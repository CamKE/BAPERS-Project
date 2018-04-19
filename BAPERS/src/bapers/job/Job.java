/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.job;

import bapers.database.DBImpl;
import bapers.gui.MainFrame;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kelvin
 */
public class Job {

    List<Material> materials;
    List<StandardJob> standardJobs;
    String jobNo;
    String UserAccountNo;
    String customerAccountNo;
    String status;
    boolean isCollected;
    Date deadlineDateRecived;
    Date deadlineCompletionTime;
    String specialInstruction;
    String[] completionTime;
    int surcharge;
    String priority;
    int userAccountNo;

    public Job(String jobNo, String UserAccountNo, String customerAccountNo, String status, boolean isCollected, Date deadlineDateRecived, Date deadlineCompletionTime, String specialInstruction) {
        this.jobNo = jobNo;
        this.UserAccountNo = UserAccountNo;
        this.customerAccountNo = customerAccountNo;
        this.status = status;
        this.isCollected = isCollected;
        this.deadlineDateRecived = deadlineDateRecived;
        this.deadlineCompletionTime = deadlineCompletionTime;
        this.specialInstruction = specialInstruction;
    }

    //this
    public Job(List<Material> materials, List<StandardJob> standardJobs, String custAccountNo, int userAccountNo, String specialInstructions, String[] completionTime, int surcharge, String priority) {
        this.materials = materials;
        this.standardJobs = standardJobs;
        this.customerAccountNo = custAccountNo;
        this.userAccountNo = userAccountNo;
        this.specialInstruction = specialInstructions;
        this.completionTime = completionTime;
        this.surcharge = surcharge;
        this.priority = priority;
    }

    public boolean create(DBImpl db, Connection conn, double total) {
        String sql;

        if (priority.equals("Stipulated")) {
            sql = "INSERT INTO completiontime(completion_time, surcharge, Priority_priority_description) VALUES ('" + completionTime[0] + ":" + completionTime[2] + "','" + surcharge + "','" + priority + "');";
            db.write(sql, conn);
        }

        sql = "INSERT INTO job(User_account_no, Customer_account_no,Deadline_CompletionTime_completion_time,special_instructions) VALUES ('" + userAccountNo + "','" + customerAccountNo + "','" + completionTime[0] + ":" + completionTime[2] + "','" + specialInstruction + "');";

        if (db.write(sql, conn) != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO job_standardjobs(StandardJob_code) VALUES");
            for (StandardJob j : standardJobs) {
                sb.append("('").append(j.getCode()).append("'),");
            }
            sb.deleteCharAt(sb.length() - 1);
            db.write(sb.toString(), conn);

            sb = new StringBuilder();
            sb.append("INSERT INTO material(material_description) VALUES");
            for (Material j : materials) {
                sb.append("('").append(j.getMaterialDescription()).append("'),");
            }
            sb.deleteCharAt(sb.length() - 1);
            db.write(sb.toString(), conn);

            System.out.println(sb.toString());

            jobNo = getJobNumber(db, conn);
            printReceipt();
            return createInvoice(db, conn, total);
        }
        return false;
    }

    private String getJobNumber(DBImpl db, Connection conn) {
        String sql = "SELECT job_no FROM job ORDER BY job_no DESC LIMIT 1";

        try (ResultSet result = db.read(sql, conn)) {
            if (result.next()) {
                return result.getString("job_no");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return "0";
    }

    public double calculateTotal() {
        double total = 0;
        float VAT = 0.2f;

        for (StandardJob sj : standardJobs) {
            total += sj.getPrice();
        }

        total *= ((surcharge / 100.0) + 1 + VAT);

        return total;
    }

    private void printReceipt() {
        Date currentDate = new Date();
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream("Job no " + jobNo + " Receipt.pdf"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.open();
            //Title
            Paragraph title = new Paragraph("Bloomsbury Image Processing Laboratory", boldFont);
            document.add(title);

            document.add(new Paragraph(Chunk.NEWLINE));
            String seperator = "--------------------------------------------------------------------------------";
            Paragraph p1 = new Paragraph(seperator);
            document.add(p1);
            /*
            --------------------------------------------------------------------------------
                            Job no: 00000018 Receipt (19-04-2018 00:39:19)
            --------------------------------------------------------------------------------
                                          Material submitted
            --------------------------------------------------------------------------------
            lncljcxnlvjxnv
            --------------------------------------------------------------------------------
            Standard Job                                                               Price
             */
            Paragraph receiptTitle = new Paragraph("       Job no: " + jobNo + " Receipt (" + dateFormat.format(currentDate) + ")");
            document.add(receiptTitle);

            document.add(p1);

            Paragraph materialHeader = new Paragraph("                             Material submitted");
            document.add(materialHeader);

            document.add(p1);

            Paragraph list = new Paragraph();

            StringBuilder sb = new StringBuilder();

            for (Material material : materials) {
                sb.append(material.getMaterialDescription()).append("\n");
            }

            list.add(sb.toString());
            document.add(list);

            list = new Paragraph();
            sb.setLength(0);

            document.add(p1);

            document.add(new Paragraph("Standard Job                                                               Price"));

            document.add(p1);

            for (StandardJob standardJob : standardJobs) {
                String desc = "(" + standardJob.getCode() + ") " + standardJob.getJobDescription();
                int length = (seperator.length() / 2) - desc.length();
                sb.append(desc);
                for (int i = length; i > 0; i--) {
                    sb.append("-");
                }
                sb.append("£").append(standardJob.getPrice());
                sb.append("\n");
            }

            list.add(sb.toString());
            document.add(list);

            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean createInvoice(DBImpl db, Connection conn, double total) {
        String sql = "INSERT INTO invoice(total_payable,invoice_location) VALUES (" + total + ",'invoice copy for customer to take')";

        return db.write(sql, conn) != 0;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<StandardJob> getStandardJobs() {
        return standardJobs;
    }

    public void setStandardJobs(List<StandardJob> standardJobs) {
        this.standardJobs = standardJobs;
    }

    public int getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(int surcharge) {
        this.surcharge = surcharge;
    }

    public String[] getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String[] completionTime) {
        this.completionTime = completionTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setUserAccountNo(int userAccountNo) {
        this.userAccountNo = userAccountNo;
    }

    public String getUserAccountNo() {
        return UserAccountNo;
    }

    public void setUserAccountNo(String UserAccountNo) {
        this.UserAccountNo = UserAccountNo;
    }

    public String getCustomerAccountNo() {
        return customerAccountNo;
    }

    public void setCustomerAccountNo(String customerAccountNo) {
        this.customerAccountNo = customerAccountNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsCollected() {
        return isCollected;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    public Date getDeadlineDateRecived() {
        return deadlineDateRecived;
    }

    public void setDeadlineDateRecived(Date deadlineDateRecived) {
        this.deadlineDateRecived = deadlineDateRecived;
    }

    public Date getDeadlineCompletionTime() {
        return deadlineCompletionTime;
    }

    public void setDeadlineCompletionTime(Date deadlineCompletionTime) {
        this.deadlineCompletionTime = deadlineCompletionTime;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    /*Joseph*/
    /**
     * @return the statusID
     */
    public int getStatusID() {
        return statusID;
    }

    /**
     * @param statusID the statusID to set
     */
    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    /**
     * @return the dateReceived
     */
    public Date getDateReceived() {
        return dateReceived;
    }

    /**
     * @param dateReceived the dateReceived to set
     */
    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    /**
     * @return the standardJobList
     */
    public ArrayList<StandardJob> getStandardJobList() {
        return standardJobList;
    }

    /**
     * @param standardJobList the standardJobList to set
     */
    public void setStandardJobList(ArrayList<StandardJob> standardJobList) {
        this.standardJobList = standardJobList;
    }

    /**
     * @return the amountOfStandardJobs
     */
    public int getAmountOfStandardJobs() {
        return amountOfStandardJobs;
    }

    /**
     * @param amountOfStandardJobs the amountOfStandardJobs to set
     */
    public void setAmountOfStandardJobs(int amountOfStandardJobs) {
        this.amountOfStandardJobs = amountOfStandardJobs;
    }

    /**
     * @return the jobNumber
     */
    public int getJobNumber() {
        return jobNumber;
    }

    /**
     * @param jobNumber the jobNumber to set
     */
    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * @return the issuedBy
     */
    public String getIssuedBy() {
        return issuedBy;
    }

    /**
     * @param issuedBy the issuedBy to set
     */
    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    /**
     * @return the deadline
     */
    public Time getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Time deadline) {
        this.deadline = deadline;
    }

    private int jobNumber;
    private String issuedBy;
    private Time deadline;
    private int amountOfStandardJobs;
    private ArrayList<StandardJob> standardJobList;
    private Date dateReceived;
    private int statusID;

    public Job(int jobNumber, Time deadline, String issuedBy, String status, Date dateReceived, boolean isCollected, int statusID) {
        this.jobNumber = jobNumber;
        this.deadline = deadline;
        this.issuedBy = issuedBy;
        this.status = status;
        this.isCollected = isCollected;
        this.dateReceived = dateReceived;
        this.statusID = statusID;
    }

    public Job(ArrayList<StandardJob> standardJobList) {
        this.standardJobList = new ArrayList<>();
    }
}

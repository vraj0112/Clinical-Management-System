package clinical.management.system;

public class GenerateBill {
    public static void main(String[] args){
        
        StringBuffer bill=new StringBuffer();
        
        StringBuffer starLine=new StringBuffer();
        for(int i=0;i<80;i++)
        {
            starLine.append("-");
        }
        
        bill.append(starLine);
        bill.append("\n");
        bill.append(String.format("|%32s",""));
        bill.append(String.format("%13.13s","THE PHARMACY"));
        bill.append(String.format("%33s|",""));
        bill.append("\n");
        bill.append(String.format("|%18s",""));
        bill.append(String.format("%40.40s","OPP.RAILWAY STATION,SAYAJIGUNJ,VADODARA"));
        bill.append(String.format("%20s|",""));
        bill.append("\n");
        bill.append(String.format("|%22s",""));
        bill.append(String.format("%31.31s","M.NO. 7890123456, 8901234567"));
        bill.append(String.format("%25s|",""));
        bill.append("\n");
        bill.append(starLine);
        bill.append("\n");
        bill.append(String.format("|%s",""));
        bill.append(String.format("%7s","Name  : "));
        bill.append(String.format("%49.49s","Bill No. : "));
        bill.append(String.format("%21s|",""));
        bill.append("\n");
        bill.append(String.format("|%s",""));
        bill.append(String.format("%7s","M.No. : "));
        bill.append(String.format("%49.49s","Date : "));
        bill.append(String.format("%21s|",""));
        bill.append("\n");
        bill.append(starLine);
        bill.append("\n");
        bill.append(String.format("|%-35.35s","Description"));
        bill.append(String.format("|%-9.9s","Qty"));
        bill.append(String.format("|%-14.14s","Rate"));
        bill.append(String.format("|%-17.17s|","Amount"));
        bill.append("\n");
        bill.append(starLine);
        bill.append("\n");
        bill.append(String.format("|%35s|",""));
        bill.append(String.format("%9s|",""));
        bill.append(String.format("%14s|",""));
        bill.append(String.format("%17s|",""));
        bill.append("\n");
        bill.append(String.format("|%35s|",""));
        bill.append(String.format("%9s|",""));
        bill.append(String.format("%14s|",""));
        bill.append(String.format("%17s|",""));
        bill.append("\n");
        bill.append(String.format("|%35s|",""));
        bill.append(String.format("%9s|",""));
        bill.append(String.format("%14s|",""));
        bill.append(String.format("%17s|",""));
        bill.append("\n");
        bill.append(String.format("|%35s|",""));
        bill.append(String.format("%9s|",""));
        bill.append(String.format("%14s|",""));
        bill.append(String.format("%17s|",""));
        bill.append("\n");
        bill.append(String.format("|%35s|",""));
        bill.append(String.format("%9s|",""));
        bill.append(String.format("%14s|",""));
        bill.append(String.format("%17s|",""));
        bill.append("\n");
        bill.append(starLine);
        bill.append("\n");
        bill.append(String.format("|%62s","Sub total : "));
        bill.append(String.format("%16s|",""));
        bill.append("\n");
        bill.append(String.format("|%30s","GET WELL SOON"));
        bill.append(String.format("%32s","Consulting fees : "));
        bill.append(String.format("%16s|",""));
        bill.append("\n");
        bill.append(String.format("|%62s","Net : "));
        bill.append(String.format("%16s|",""));
        bill.append("\n");
        bill.append(starLine);
        bill.append("\n");
        bill.append(String.format("|%s",""));
        bill.append(String.format("%78s|",""));
        bill.append("\n");
        bill.append(String.format("|%58s",""));
        bill.append(String.format("%20.20s","For Pharmacy Use"));
        bill.append(String.format("%s|",""));
        bill.append("\n");
        bill.append(starLine);
        
        System.out.println(bill);
    }
}

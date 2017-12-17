import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class LCS_Rohit_Sebastian_rohitjos {
	
	class OutputVO{
		String lcs;
		int length;
	}
	
	

	public OutputVO calculateLCS(String s1, String s2)
	{
		int l1 = s1.length();
		int l2 = s2.length();
		int i = 0, j = 0;
		int[][] opt = new int[l1+1][l2+1];
		OutputVO output=new OutputVO();
		for (j = 0; j < l2; ++j) {
			opt[0][j] = 0;
		}
		
		for (i = 1; i <= l1; ++i) {
			opt[i][0] = 0;
			for(j=1;j<=l2;++j)
			{
				if (s1.charAt(i-1)==s2.charAt(j-1)) {
					opt[i][j]=opt[i-1][j-1]+1;
				}
				else
				{
					opt[i][j]=Math.max(opt[i][j-1], opt[i-1][j]);
				}
			}
		}
		String subString="";
		i=l1;
		j=l2;
		while(i>0&&j>0){
				if (opt[i][j]>opt[i-1][j]&&opt[i][j]>opt[i][j-1]) {
					subString+=s1.charAt(i-1);
					--i;
					--j;
				}
				else if(opt[i][j]==opt[i-1][j])
				{
					--i;
				}
				else if(opt[i][j]==opt[i][j-1])
				{
					--j;
				}
		}
		output.length=subString.length();
		String op="";
		for(i=subString.length()-1;i>=0;--i)
		{
			op+=subString.charAt(i);
		}
		output.lcs=op;
		return output;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		String str;
		LCS_Rohit_Sebastian_rohitjos subSq= new LCS_Rohit_Sebastian_rohitjos();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			String[] ip = new String[2];
			int count=0;
			while ((str = in.readLine()) != null) {
				if(count<2)
				{
				ip[count]=str;
				++count;
				}
			}
			
				OutputVO output=subSq.calculateLCS(ip[0],ip[1]);
				Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF8"));
				out.write(output.length+"\n");
				out.write(output.lcs);
				out.close();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

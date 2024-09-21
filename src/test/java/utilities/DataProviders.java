package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders
{
	//String path = "C:\\Users\\pratham.kale\\eclipse-workspace\\OpencartV_01\\testData\\usernamedata.xlsx";
	/*
	public DataProviders(String path) 
	{
		super(path);
		// TODO Auto-generated constructor stub
	}
	*/
	
	@DataProvider(name="LoginData")
	//Data Provider 01 - login data
	public Object [][] getdata() throws IOException
	{
		String path = "C:\\Users\\pratham.kale\\eclipse-workspace\\OpencartV_01\\testData\\usernamedata.xlsx";
		ExcelUtility exelutil = new ExcelUtility(path);
		
		int totalrows = exelutil.getrowcount("Sheet1");
		int totalcols = exelutil.getcellcount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols]; 
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j] = exelutil.getcelldata("Sheet1", i,j );
			}
		}
		return logindata;
		
		
	}

}

/* Name: Alex Spiegl
Course: CNT 4714 – Spring 2023 – Project Four
Assignment title: A Three-Tier Distributed Web-Based Application
Date: April 24, 2023
*/

package project4;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetToHTML {

	public static synchronized String getHtmlRows(ResultSet results) throws SQLException {
		int color = 0;
		StringBuffer strBuf = new StringBuffer();
		ResultSetMetaData metaData = results.getMetaData();
		int columnCount = metaData.getColumnCount();
		strBuf.append("<tr bgcolor=#FF0000 align=center>");
		for(int i = 1; i <= columnCount; i++) {
			strBuf.append("<td><b>" + metaData.getColumnName(i) + "</td>");
		}
		strBuf.append("</tr>");
		while (results.next()) {
			if ((color % 2) == 0) {
					strBuf.append("<tr bgcolor=#D3D3D3 align=center>");
				} else {
					strBuf.append("<tr bgcolor=#FFFFFF align=center>");
				}
			for(int i=1; i<= columnCount; i++ ) {
				strBuf.append("<td>" + results.getString(i) + "</td>");
			}
			strBuf.append("</tr>");
			color++;
		}
		strBuf.append("</tr>");
		return strBuf.toString();
	}
}
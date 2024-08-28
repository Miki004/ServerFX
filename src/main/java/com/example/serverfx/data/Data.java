package com.example.serverfx.data;
import com.example.serverfx.distance.InvalidSizeException;
import com.example.serverfx.database.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Data {
	private List<Example> data = new ArrayList<Example>();
	private int numberOfExamples;

	public Data(DbAccess db,String tableName) throws NoDataException{
		try {
			TableData tabella=new TableData(db);
			 this.data= tabella.getDistinctTransazioni(tableName);
			 db.closeConnection();
			 this.numberOfExamples=data.size();
		}catch (MissingNumberException | SQLException | EmptySetException e) {
			throw  new NoDataException();
		}


    }

	public int getNumberOfExamples() {
		return numberOfExamples;
	}

	public Example getExample(int exampleIndex) {
		return data.get(exampleIndex);

	}

	public double [][] distance() throws InvalidSizeException {
		double [][] matriangsup=new double [getNumberOfExamples()][getNumberOfExamples()];
		int j;
		int i=0;
		while(i<getNumberOfExamples()-1) {
			j=i+1;
			while(j<=getNumberOfExamples()-1) {
				matriangsup[i][j]=data.get(i).distance(data.get(j));
				j++;
			}
			i++;
		}
		return matriangsup;
	}

	public String toString() {
		StringBuilder str= new StringBuilder();
		int i=0;
        for (Example datum : data) {
            i++;
            str.append(i).append(":").append("[").append(datum.toString()).append("]").append("\n");

        }
		return str.toString();
	}

}

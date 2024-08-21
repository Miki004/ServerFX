package com.example.serverfx.data;
import com.example.serverfx.distance.InvalidSizeException;
import com.example.serverfx.database.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Data {
	private List<Example> data = new ArrayList<Example>();
	//private Example data []; rappresenta il
	
	private int numberOfExamples;// numero di esempi nel
	public Data(String tableName) throws NoDataException{
		try {
			DbAccess db=new DbAccess();
			TableData tabella=new TableData(db);
			 this.data= tabella.getDistinctTransazioni(tableName);
			 this.numberOfExamples=data.size();
		}catch (DatabaseConnectionException | MissingNumberException | SQLException e) {
			throw  new NoDataException();
		}catch (EmptySetException e) {
			throw  new NoDataException();
		}


    }
	/*
	public Data() {

		Example e=new Example();
		e.add(1.0);
		e.add(2.0);
		e.add(0.0);
		data.add(e);

		e=new Example();
		e.add(0.0);
		e.add(1.0);
		e.add(-1.0);
		data.add(e);

		e=new Example();
		e.add(1.0);
		e.add(3.0);
		e.add(5.0);
		data.add(e);

		e=new Example();
		e.add(1.0);
		e.add(3.0);
		e.add(4.0);
		data.add(e);

		e=new Example();
		e.add(2.0);
		e.add(2.0);
		e.add(0.0);
		data.add(e);

		numberOfExamples=5;
	}
	*/

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

	public static void main(String[] args) {
		try {

			String nomeTabella = Keyboard.readString();
			Data trainingSet = new Data(nomeTabella);
			System.out.println(trainingSet);
			try {
				double[][] distancematrix = trainingSet.distance();
				System.out.println("Distance matrix:\n");
				for (int i = 0; i < distancematrix.length; i++) {
					for (int j = 0; j < distancematrix.length; j++) {
						System.out.print(distancematrix[i][j] + "\t");
					}
					System.out.println("");
				}
			} catch (InvalidSizeException e) {
				System.out.println("impossibile calcolare la distanza,dimensioni diverse");
			}

		}catch (NoDataException e) {
			System.out.println(e.getMessage());
		}
	}

}

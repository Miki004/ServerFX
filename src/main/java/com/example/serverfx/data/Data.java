package com.example.serverfx.data;
import com.example.serverfx.distance.InvalidSizeException;
import com.example.serverfx.database.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Data rappresenta un insieme di esempi estratti da un database.
 * Ogni esempio è rappresentato da un'istanza della classe {@link Example}.
 * La classe fornisce metodi per ottenere informazioni sugli esempi e per calcolare
 * le distanze tra di essi.
 */
public class Data {
	private List<Example> data = new ArrayList<Example>();
	private int numberOfExamples;

	/**
	 * Costruttore che inizializza l'oggetto Data estraendo esempi distinti
	 * da una tabella specificata in un database.
	 *
	 * @param db        L'oggetto {@link DbAccess} che gestisce la connessione al database.
	 * @param tableName Il nome della tabella da cui estrarre i dati.
	 * @throws NoDataException Se si verifica un problema durante l'estrazione dei dati
	 *                         o se la tabella è vuota.
	 */
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

	/**
	 * Restituisce il numero totale di esempi presenti nell'oggetto Data.
	 *
	 * @return Il numero di esempi.
	 */
	public int getNumberOfExamples() {
		return numberOfExamples;
	}

	/**
	 * Restituisce un esempio specifico dall'insieme di dati, identificato dal suo indice.
	 *
	 * @param exampleIndex L'indice dell'esempio da restituire.
	 * @return L'oggetto {@link Example} all'indice specificato.
	 */
	public Example getExample(int exampleIndex) {
		return data.get(exampleIndex);

	}

	/**
	 * Calcola la matrice triangolare superiore delle distanze tra tutti gli esempi
	 * presenti nell'oggetto Data.
	 *
	 * @return Una matrice bidimensionale contenente le distanze tra gli esempi.
	 * @throws InvalidSizeException Se la dimensione della matrice non è valida.
	 */
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

	/**
	 * Restituisce una rappresentazione testuale dell'oggetto Data,
	 * con tutti gli esempi elencati e numerati.
	 *
	 * @return Una stringa che rappresenta l'oggetto Data.
	 */
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

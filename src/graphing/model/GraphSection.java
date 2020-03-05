package graphing.model;

import graphing.exceptions.IDAlreadyExistsException;
import graphing.exceptions.TooManyDataSetsException;

import java.util.ArrayList;
import java.util.Random;

public abstract class GraphSection<DataSetType extends GraphData> {
    private ArrayList<DataSetType> dataSets;

    public GraphSection(ArrayList<DataSetType> dataSets) {
        this.dataSets = dataSets;
    }

    public int getUniqueID() {
        if (dataSets.size() > 10000) {
            throw new TooManyDataSetsException("Dataset count cannot be above 10000");
        }
        int idNum;
        Random random = new Random();
        idNum = random.nextInt(10000);
        while (containsIDNum(idNum)) {
            idNum = random.nextInt(10000);
        }
        return idNum;
    }

    public boolean containsIDNum(int idNum) {
        return dataSets.stream().anyMatch(x -> x.getIdNum() == idNum);
    }

    public void addDataSet(DataSetType dataSet) {
        if (containsIDNum(dataSet.getIdNum())) {
            throw new IDAlreadyExistsException("Dataset ID already exists in list");
        }
        dataSets.add(dataSet);
    }

    public boolean removeDataSet(int idNum) {
        return dataSets.removeIf(x -> x.getIdNum() == idNum);
    }

    public ArrayList<DataSetType> getDataSets() {
        return dataSets;
    }
}

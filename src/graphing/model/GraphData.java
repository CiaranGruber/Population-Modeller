package graphing.model;

import java.util.ArrayList;

public abstract class GraphData<DataType extends DataPoint> {
    protected ArrayList<DataType> data;
    private int idNum;
    private String dataName;

    public GraphData(int idNum) {
        this("", idNum);
    }

    public GraphData(String dataName, int idNum) {
        this(dataName, new ArrayList<>(), idNum);
    }

    public GraphData(String dataName, ArrayList<DataType> data, int idNum) {
        this.idNum = idNum;
        this.dataName = dataName;
        this.data = data;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public void addDataPoint(DataType dataPoint) {
        data.add(dataPoint);
    }

    public boolean removeDataPoint(DataType dataPoint) {
        return data.remove(dataPoint);
    }

    public ArrayList<DataType> getData() {
        return data;
    }
}

package com.campus.optimizer.db.models;

/** Represents a row of the `algorithm_runs` table - empirical timing evidence (M10). */
public class AlgorithmRun {

    private final String runId;
    private final String algorithmName;
    private final int inputSize;
    private final long timeNs;
    private final double memoryKb;
    private final String dateRun;

    public AlgorithmRun(String runId, String algorithmName, int inputSize,
                         long timeNs, double memoryKb, String dateRun) {
        this.runId = runId;
        this.algorithmName = algorithmName;
        this.inputSize = inputSize;
        this.timeNs = timeNs;
        this.memoryKb = memoryKb;
        this.dateRun = dateRun;
    }

    public String getRunId() { return runId; }
    public String getAlgorithmName() { return algorithmName; }
    public int getInputSize() { return inputSize; }
    public long getTimeNs() { return timeNs; }
    public double getMemoryKb() { return memoryKb; }
    public String getDateRun() { return dateRun; }

    @Override
    public String toString() {
        return "AlgorithmRun{" + runId + ", " + algorithmName + ", n=" + inputSize +
                ", " + timeNs + "ns, " + memoryKb + "KB}";
    }
}

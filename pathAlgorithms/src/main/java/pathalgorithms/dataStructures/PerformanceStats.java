/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms.dataStructures;

/**
 *
 * @author mikko
 */
public class PerformanceStats {

    private final long[] values;
    private final String name;
    private double average;
    private double standardDev;
    private long minimum;
    private long maximum;
    private int index;

    public PerformanceStats(String name, int length) {
        this.values = new long[length];
        this.index = 0;
        this.name = name;
    }

    public void setValue(int index, long value) {
        if (this.values != null) {
            this.values[index] = value;
        }
    }
    
    public void addValue(long value) {
        this.values[this.index++] = value;
    }

    public void computeStats() {
        this.average = 0;
        this.minimum = Long.MAX_VALUE;
        this.maximum = 0;
        this.standardDev = 0;

        for (int i = 1; i < this.values.length; i++) {
            average += this.values[i];
            if (this.values[i] > this.maximum) {
                this.maximum = this.values[i];
            }
            if (this.values[i] < this.minimum) {
                this.minimum = this.values[i];
            }
        }

        this.average /= this.values.length - 1;

        for (double val : this.values) {
            this.standardDev += Math.pow(val - this.average, 2);
        }

        this.standardDev = Math.sqrt(this.standardDev / (this.values.length - 1));

    }

    @Override
    public String toString() {
        return this.name + "\n\tAverage: " + this.average + "(ns), "
                + this.average / 1e9 + "(s)\n\tStandard deviation: "
                + this.standardDev + "\n\tMinimum and maximum: "
                + this.minimum + ", " + this.maximum;
    }
}

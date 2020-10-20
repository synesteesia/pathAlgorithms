/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms.IO;

/**
 *
 * @author mikko
 */
public class PerformanceStats {

    private final long[] values;
    private double average;
    private double standardDev;
    private long minimum;
    private long maximum;

    public PerformanceStats(int length) {
        this.values = new long[length];
    }

    public void setValue(int index, long value) {
        if (this.values != null) {
            this.values[index] = value;
        }
    }

    public void computeStats() {
        this.average = 0;
        this.minimum = Long.MAX_VALUE;
        this.maximum = 0;
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
    }

    @Override
    public String toString() {
        return "\tAverage: " + this.average + "(ns), " + this.average / 1e9
                + "(s)\n\tStandard deviation: " + this.standardDev
                + "\n\tMinimum and maximum: " + this.minimum + ", "
                + this.maximum;
    }
}

package javaCore.jfreechart;

import org.jfree.data.general.DefaultPieDataset;

/**
 * @Description:
 * @Author: zyw
 * @Date: 2018/2/27
 */
public class jfreechartTest {

    public static void main(String[] args) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Category 1", 43.2);
        dataset.setValue("Category 2", 27.9);
        dataset.setValue("Category 3", 79.5);

    }
}

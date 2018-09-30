package com.locydragon.lagg.neural;

import com.locydragon.lagg.listeners.ache.Ache;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.LMS;
import org.neuroph.util.TransferFunctionType;

import java.io.File;
import java.io.IOException;

public class NeuralNetwork {
	public static final String dataFileURL = ".//plugins//LocyClearLagg//Data//Neural.dat";
	public static org.neuroph.core.NeuralNetwork actor
			= new MultiLayerPerceptron(TransferFunctionType.TANH, 2, 10, 1);
	public static DataSet dataTraining = new DataSet(2, 1);
	public static File dataFile;
	static {
		dataFile = new File(dataFileURL);
		if (!dataFile.exists()) {
			dataFile.getParentFile().mkdirs();
			try {
				dataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//TODO learn normal learning value for actor
			actor = new MultiLayerPerceptron(TransferFunctionType.TANH, 2, 10, 1);
			{
				dataTraining.addRow(new double[]{3586, 218.780}, new double[]{1});
				dataTraining.addRow(new double[]{3587, 208.780}, new double[]{1});
				dataTraining.addRow(new double[]{3400, 331.780}, new double[]{1});
				dataTraining.addRow(new double[]{3587, 208.780}, new double[]{1});
			}
			{
				dataTraining.addRow(new double[]{3803, 6.6}, new double[]{0});
				dataTraining.addRow(new double[]{3839, 2.0}, new double[]{0});
				dataTraining.addRow(new double[]{3812, 218.0}, new double[]{0});
				dataTraining.addRow(new double[]{3807, 70.0}, new double[]{0});
				dataTraining.addRow(new double[]{3721, 56.0}, new double[]{0.2});
				dataTraining.addRow(new double[]{0, 10000.0}, new double[]{0});
				dataTraining.addRow(new double[]{255, 28674.0}, new double[]{0});
			}
			((LMS)actor.getLearningRule()).setMaxError(0.001);
			actor.learn(dataTraining);
			actor.save(dataFileURL);
		}
		actor = MultiLayerPerceptron.createFromFile(dataFileURL);
		Thread asyncSave = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(10 * 1000);
					actor.save(dataFileURL);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		asyncSave.setDaemon(true);
		asyncSave.start();
		Ache.loadThreads.add(asyncSave);
	}

	public static void init() {}
}

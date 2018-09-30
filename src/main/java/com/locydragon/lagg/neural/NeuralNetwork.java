package com.locydragon.lagg.neural;

import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import java.io.File;
import java.io.IOException;

public class NeuralNetwork {
	public static final String dataFileURL = ".//plugins//LocyClearLagg//Data//Neural.dat";
	public static MultiLayerPerceptron actor
			= new MultiLayerPerceptron(TransferFunctionType.TANH, 5, 14, 1);
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
		} else {
			dataTraining =
					DataSet.createFromFile(dataFileURL, 2, 1, "\t", true);
			actor.learn(dataTraining);
		}
	}

	public static void init() {}
}

package com.locydragon.lagg.neural;

import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

public class NeuralNetwork {
	public static MultiLayerPerceptron actor
			= new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 5, 14, 1);
	DataSet dataTraining = new DataSet(5, 1);
	static {

	}
}

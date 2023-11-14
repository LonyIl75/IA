//@ts-nocheck
	export interface ILoss 
	{
		group:string,
		labels:Array<string>,
	}

	export const Losses  = [
		{
		group:"Probabilistic losses",
		
		labels:["Binary crossentropy", "Categorical crossentropy ","Sparse categorical crossentropy ","Poisson ","KLDivergence"]

		},

		{
			group:"Regression losses",

			labels:["MeanSquared Error ","Mean absolute error ","MeanSquared logarithmic error","Cosine similarity ","Huber ","Log cosh" ]
		},

			{
			group:"Hinge losses",

			labels:["Hinge ","Squared hinge ","Categorical hinge "]

		}


	]
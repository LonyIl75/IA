
import * as help from "./../../help/IntefaceUtil"


export const Optimizers = [

 'Adadelta',//: Optimizer that implements the Adadelta algorithm.

 'Adagrad',//: Optimizer that implements the Adagrad algorithm.

 'Adam',//: Optimizer that implements the Adam algorithm.

 'Adamax',//: Optimizer that implements the Adamax algorithm.

 'Ftrl',//: Optimizer that implements the FTRL algorithm.

 'Nadam',//: Optimizer that implements the NAdam algorithm.

 //Optimizer:Optimizer,//: Base class for Keras optimizers.

 'RMSprop',//: Optimizer that implements the RMSprop algorithm.

 'SGD',//: Gradient descent (with momentum) optimizer.
]

export const Clip= [
    'clipnorm',
    'clipvalue'//, global_clipnorm
]//: Optimizer that implements the Adadelta algorithm.

export interface IConfigOptimizer extends help.Module {
    learning_rate:number,
    //keyword:Array<Clip>

}
interface IxAdam extends IConfigOptimizer{
    beta_1:number,//0.9,
    beta_2:number,//0.999,
    epsilon:number,//1e-07,
}

interface IConfigAdamax extends  IxAdam{}
interface IConfigNadam extends  IxAdam{}
interface IConfigAdam extends IxAdam {amsgrad:boolean}


interface IConfigAdadelta extends IConfigOptimizer{
    rho:number,//0.95
    epsilon:number,//1e-07 

}
interface IConfigAdagrad extends IConfigOptimizer{
    initial_accumulator_value:number , //0.1
    epsilon:number,//1e-07,
    
}

interface IConfigMomemtum extends IConfigOptimizer{momentum:number}

interface IConfigRMSprop extends IConfigMomemtum{
    rho:number,//0.9,
    epsilon:number,//1e-07,
    centered:boolean
    // **kwargs
}

interface IConfigSGD extends IConfigOptimizer {nesterov:boolean}

interface IConfigFtrl extends IConfigOptimizer {

    learning_rate_power:number//-0.5,<= 0
    initial_accumulator_value:number//0.1, >=0
    l1_regularization_strength:number//0.0,>= 0 float
    l2_regularization_strength:number//0.0,>=0 float
    l2_shrinkage_regularization_strength:number//0.0,>=0 float
    beta:number//0.0, float
}


 
export interface IAdadelta extends help.IGeneric<string,IConfigAdadelta>{};

export interface IAdagrad extends help.IGeneric<string,IConfigAdagrad>{};

export interface IAdam extends help.IGeneric<string,IConfigAdam>{};

export interface IAdamax extends help.IGeneric<string,IConfigAdamax>{};

export interface IFtrl extends help.IGeneric<string,IConfigFtrl>{};

export interface INadam extends help.IGeneric<string,IConfigNadam>{};

export interface IRMSprop extends help.IGeneric<string,IConfigRMSprop>{};

export interface ISGD extends help.IGeneric<string, IConfigSGD>{ };



import * as help from "./../../help/IntefaceUtil"
import {ILayer} from "./../../Model/Interface/Model"
import defaultLayers from"./../Data/defaultLayers"
export const Initializers = [
    'Constant',//: Initializer that generates tensors with constant values.

    'GlorotNormal',//: The Glorot normal initializer also called Xavier normal initializer.

    'GlorotUniform',//: The Glorot uniform initializer also called Xavier uniform initializer.

    'HeNormal',//: He normal initializer.

    'HeUniform',//: He uniform variance scaling initializer.

    'Identity',//: Initializer that generates the identity matrix.

    'Initializer',//: Initializer base  all Keras initializers inherit from this .

    'LecunNormal',//: Lecun normal initializer.

    'LecunUniform',//: Lecun uniform initializer.

    'Ones',//: Initializer that generates tensors initialized to 1.

    'Orthogonal',//: Initializer that generates an orthogonal matrix.

    'RandomNormal',//: Initializer that generates tensors with a normal distribution.

    'RandomUniform',//: Initializer that generates tensors with a uniform distribution.

    'TruncatedNormal',//: Initializer that generates a truncated normal distribution.

    'VarianceScaling',//: Initializer capable of adapting its scale to the shape of weights tensors.

    'Zeros',//: Initializer that generates tensors initialized to 0.

    'constant',//: Initializer that generates tensors with constant values.

    'glorot_normal',//: The Glorot normal initializer also called Xavier normal initializer.

    'glorot_uniform',//: The Glorot uniform initializer also called Xavier uniform initializer.

    'he_normal',//: He normal initializer.

    'he_uniform',//: He uniform variance scaling initializer.

    'identity',//: Initializer that generates the identity matrix.

    'lecun_normal',//: Lecun normal initializer.

    'lecun_uniform',//: Lecun uniform initializer.

    'ones',//: Initializer that generates tensors initialized to 1.

    'orthogonal',//: Initializer that generates an orthogonal matrix.

    'random_normal',//: Initializer that generates tensors with a normal distribution.

    'random_uniform',//: Initializer that generates tensors with a uniform distribution.

    'truncated_normal',//: Initializer that generates a truncated normal distribution.

    'variance_scaling',//: Initializer capable of adapting its scale to the shape of weights tensors.

    'zeros',//: Initializer that generates tensors initialized to 0.
]
export const Regularizers = [

    'L1',//: A regularizer that applies a L1 regularization penalty.

    'L1L2',//: A regularizer that applies both L1 and L2 regularization penalties.

    'L2',//: A regularizer that applies a L2 regularization penalty.

    'Regularizer',//: Regularizer base .

    'l1',//: A regularizer that applies a L1 regularization penalty.

    'l2',//: A regularizer that applies a L2 regularization penalty.
]
export const Constraints = [
    'Constraint',

    'MaxNorm',//: MaxNorm weight constraint.

    'MinMaxNorm',//: MinMaxNorm weight constraint.

    'NonNeg',//: Constrains the weights to be non-negative.

    'RadialConstraint',//: Constrains Conv2D kernel weights to be the same for each radius.

    'UnitNorm',//: Constrains the weights incident to each hidden unit to have unit norm.

    'max_norm',//: MaxNorm weight constraint.

    'min_max_norm',//: MinMaxNorm weight constraint.

    'non_neg',//: Constrains the weights to be non-negative.

    'radial_constraint',//: Constrains Conv2D kernel weights to be the same for each radius.

    'unit_norm',//: Constrains the weights incident to each hidden unit to have unit norm.
]




export const layer = [
    'conv2d',
    'maxPool',
    'dense',
    'flatten',
    'dropout',
    'inputLayer'
]




interface IConfigLayer extends ILayer{} 




export const activations =[
'deserialize',//: Returns activation function given a string identifier.

'elu',//: Exponential Linear Unit.

'exponential',//: Exponential activation function.

'gelu',//: Applies the Gaussian error linear unit (GELU) activation function.

'get',//: Returns function.

'hard_sigmoid',//: Hard sigmoid activation function.

'linear',//: Linear activation function (pass-through).

'relu',//: Applies the rectified linear unit activation function.

'selu',//: Scaled Exponential Linear Unit (SELU).

'serialize',//: Returns the string identifier of an activation function.

'sigmoid',//: Sigmoid activation function sigmoid(x) = 1 / (1 + exp(-x)).

'softmax',//: Softmax converts a real vector to a vector of categorical probabilities.

'softplus',//: Softplus activation function softplus(x) = log(exp(x) + 1).

'softsign',//: Softsign activation function softsign(x) = x / (abs(x) + 1).

'swish',//: Swish activation function swish(x) = x * sigmoid(x).

'tanh',//: Hyperbolic tangent activation function.


]

interface IActivationLayer extends IConfigLayer{
    
    activation:string;
    activity_regularizer:help.IGeneric<string,object> ; //flemme d'ecrire le reste : example object : {seed:Node}

    kernel_initializer :help.IGeneric<string,object> ;
    kernel_constraint:help.IGeneric<string,object> ;
    kernel_regularizer:help.IGeneric<string,object> ;

    use_bias:boolean;

    bias_initializer?:help.IGeneric<string,object> ;
    bias_constraint?:help.IGeneric<string,object> ;
    bias_regularizer?:help.IGeneric<string,object> ;

    
  
}

interface  IConfigDropout extends IConfigLayer{
    rate:number;
    noise_shape:[number|null,number,number,number];
}


interface IConfigConv2D extends IActivationLayer ,help.IInput {
    filters:number;
    kernel_size:[number,number];
    groups:number;
    dilatation_rate:[number,number];
    strides:[number,number]

}


interface IConfigDense extends IActivationLayer {
   units:number;

}
interface IConfigFlatten extends help.IInput,IConfigLayer {

}


interface IConfigMaxPool extends IConfigLayer {
 pool_size: [number,number]; //{{"class_name": "__tuple__", "items": [2, 2]} ;
   strides: [number,number];

}




interface IConfigInput extends IConfigLayer{
  /*
  input_shape=None, batch_size=None, dtype=None, input_tensor=None, sparse=False,
    name=None, ragged=False, **kwargs
    */ //TODO IMPORTANT 
    batch_input_shape:[number,number,number,number];
    sparse:boolean;
    ragged:boolean;

}

export interface IInputLayer extends help.IGeneric<string,IConfigInput>{};
export interface IDropout extends help.IGeneric<string,IConfigDropout>{};
export interface IConv2D extends help.IGeneric<string,IConfigConv2D> {};
export interface IDense extends help.IGeneric<string,IConfigDense> {};
export interface IFlatten extends help.IGeneric<string,IConfigFlatten> {};
export interface IMaxPool extends help.IGeneric<string,IConfigMaxPool> {};

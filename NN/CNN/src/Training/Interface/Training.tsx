//@ts-nocheck
import * as help from "./../../help/IntefaceUtil"
import {ITruc} from "./Metrics"
import {IConfigOptimizer} from "./Optimizers"

export interface IConfigDataset {

    name: string,
    versions: string,
    size: number,
    featuresDict: any
}

export interface ICustomParam{
    batch_size:number;
    epoch:number;
}
export interface IInputHeader{
    dataset_config: help.IGeneric<string, IConfigDataset>;
    custom_param:ICustomParam
}
/*export interface ITrainingConfig {
    loss: string;//loss_functionor
    metrics: Array<help.IGeneric<string, ITruc>>[];
    weighted_metrics: any;
    loss_weights: any;
    optimizer_config: help.IGeneric<string, IConfigOptimizer>;
    input_header:IInputHeader;
  
   
};*/
export interface ITrainingConfig {
loss:string;//loss_functionor
metrics:Array<help.IGeneric<string,ITruc>>[];
weighted_metrics:any;
loss_weights:any;
optimizer_config:help.IGeneric<string,IConfigOptimizer>;
input_header: IInputHeader//help.IGeneric<string,IConfigInputHeader>;
};




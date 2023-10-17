
import  {ITrainingConfig}  from "./../../Training/Interface/Training"
import * as help from "./../../help/IntefaceUtil"


import defaultLayer from "./../../Layers/Data/defaultLayers"


//MODEL:

interface IConfigModel extends help.Module {
    layers: Array<help.IGeneric<string, ILayer>>;

}

export const Models = [
    "Sequential",
    "Functional"

]

//LAYER :

export interface ILayer extends help.Module {
    trainable: boolean;
    dtype: string;
    dynamic: boolean;

}



export interface IModel extends help.IGeneric<string, IConfigModel>, ILayer {
    expects_training_arg: boolean;
    batch_input_shape: [number, number, number, number];
    must_restore_from_config: boolean;
    training_config: ITrainingConfig;

}





  /*const Activity ={...defaultLayer[o.class_name].config}
      type Activity = typeof Activity;

           Object.keys(Activity).map((key) => {

//generic
console.log(typeof Activity[key]);

   switch(typeof Activity[key] ){
       case 'number':
                Activity[key]=parseInt(Activity[key],10)
                break;
        case '[number,number]':
        case'[number,number,number,number]':
                Activity[key]=o.length === 0?new Array():o.replace(/, +/g, ",").split(",").map(Number);
                break;

        case 'object':
                secourConversion( Activity[key])
                break;

           
        default:
            break;s
   }
  

})*/
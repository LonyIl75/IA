//@ts-nocheck
import React  ,{ useState,useEffect } from'react'
import {Losses ,ILoss}  from "./Data/Losses"
import defaultOptimizer from "./Data/defaultOptimizers"
import {useForm,Controller,useFieldArray} from "react-hook-form"
import {ITrainingConfig} from "./Interface/Training"
import{IConfigOptimizer} from "./Interface/Optimizers"
import _ from 'lodash'
//import { keys } from 'ts-transformer-keys'; //marche que sur webpack ?
import {SwitchOptimizer} from "./view/Optimizer/SwitchOptimizer"
import {InputHeader} from "./view/InputHeader/InputHeader"
import Dataset from "./Data/Dataset"
import { yupResolver } from "@hookform/resolvers/yup";
import * as Yup from "yup";
import "./style/cnul.css" 
import {TrainingConfig} from "./verification/Training"
import { yellow } from '@material-ui/core/colors'


function capitalizeFirstLetter(str:string) {
  return str.charAt(0).toUpperCase() + str.slice(1);
}
export interface BaseProps{
    register:any,
    path:string,
    errors:any

}

export interface SelectProps extends BaseProps{
setValue:Function,
unregister:Function
}



enum typeLoss{
    class,function,other
}

let selectLoss:Array<string>=Object.keys(typeLoss).reduce((arr:Array<string>, key:any) => {
  if (!arr.includes(key)) {
    arr.push(typeLoss[key]);
  }
  return arr;
}, []);




const selectSwitch  = (methodType:typeLoss) =>{
  switch(methodType){
    case typeLoss.function:
     let opt= _.map(Losses,function(loss:ILoss){return {group:loss.group , 
    labels:_.map(loss.labels,(label:string)=>_.snakeCase(label))}})
    let extra ={
        group:'Other Losses',
        labels:["msle","serialize","mse","mean_absolute_percentage_error","mape","mae","logcosh","kullback_leibler_divergence","kld","get","deserialize","MSLE","MSE","MAPE","MAE","KLD"]
    }
    return [...opt,extra];
    case typeLoss.class:
      return _.map(Losses,function(loss:ILoss){
        return {group:loss.group , 
    labels:_.map(loss.labels,(label:string)=>capitalizeFirstLetter(_.camelCase(label)))}});
    case typeLoss.other:
        return[{group:"Beta",labels:["Pas encore implemente"]}];
    default:
      return[{group:"Error",labels:["Out of bound"]}];

  }
}

const IFRAME ={

  borderStyle:"none",width: "100%" ,height: "100%"/*,display:"block"*/
}

export const Training:React.FC = () => {

    
     const { register, handleSubmit, setValue,control,unregister,getValues,  formState:{ errors }} = useForm<ITrainingConfig>({
     resolver: yupResolver(TrainingConfig),    
      defaultValues:{
             loss:"BinaryCrossentropy",
             metrics:[[]],
             weighted_metrics: null,
             loss_weights: null, 
             optimizer_config: {class_name:"Adam",config:{...defaultOptimizer["Adam"]}},
             input_header:{
               dataset_config:{
               class_name:"mnist",
               config:{...Dataset["mnist"]}
             },
            custom_param:{
              epoch:5,
              batch_size:10
            }
          }

         }}
    );
    /* const { fields, append,remove} = useFieldArray({
    control,
    name: "metrics"
  });*/

   
    const [lossType ,setLossType]=useState<typeLoss>(typeLoss.class);
    const [options,setOptions]=useState<Array<ILoss>>();
    const [opti,setOpti]=useState<IConfigOptimizer>();


    useEffect(()=>{
      const tmp= selectSwitch(lossType)
        setOptions(tmp)
        setValue("loss",tmp[0].labels[0])
      
    },[lossType]);

    
const onSubmit = (data:any) => {
  console.log("validation de :")
  console.log(data);
console.log("apres validation:")
   console.log(TrainingConfig.validateSync({...data}))
console.log("erreurs: ")
console.log(errors||"aucune erreur")

}


    return (
      <div>
      <table style={ {display: "flex-box",height: "100%",width:"100%",verticalAlign:"bottom"}}>
      <tr>
        <th>Training Config:</th>
        <th>Visualisation</th>
        </tr>
        <tr style={{verticalAlign:"middle"}} >
        <td>
                      <form onSubmit={handleSubmit(onSubmit)} >
            <div>
                <label>Loss: </label>
                <select onChange={(e:React.ChangeEvent<HTMLInputElement>)=>{setLossType(typeLoss[e.target.value])}}>
                {selectLoss.map((value:string,key:any)=>{return(<option value={value} key={key}>{value}</option>)})}
                </select>
                <select {...register("loss")} >
            {options?.map((item_group:ILoss,index:number)=>{ 

                return (    <optgroup label={item_group.group} key={index}>
                            {item_group.labels.map((label:string,key:number)=>{return(<option key={`${index}:${key}`} value={label} >{label}</option>)})}
                            </optgroup>)
                })}
          </select>
                <div>
                <h3>Metrics:</h3>
                <ul>


                            { /*fields.map((item, index) => {
                            item.map((elem,index2)=>{
                            return (
                            <li key={`${index}:${index2}`}>
                                <select {...register(`metrics.${index}.${index2}.class_name`)}>
                                </select>
                                <input {...register(`metrics.${index}.${index2}.config.name`)}/>
                                </li>
                            )})})*/}
                </ul>
                </div>
                <SwitchOptimizer register={register}  setValue={setValue} path={"optimizer_config"} unregister={unregister} />
            </div>
            <div>
              <h3>Input </h3>
              <InputHeader register={register} path={"input_header"} errors ={errors} setValue={setValue} unregister={unregister}/>
              </div>
            <input type="submit" />
            </form>
                   </td>

          <td style={{verticalAlign:"middle"}}>

<iframe src="/umap_html/Layer/dense.html" title="description" style={IFRAME} ></iframe>

</td>
</tr>

</table>
    </div>
    )
}



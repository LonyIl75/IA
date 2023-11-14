//@ts-nocheck
import React  ,{ useState,useEffect } from'react'
import {Optimizers as enumOpti,Clip as enumClip} from "./../../Interface/Optimizers"
import {GenericInput} from "./GenericInput"

//TODO: remplacer par une fonction dans helper :

let selectOpti=Object.keys(enumOpti).reduce((arr:Array<string>, key:any) => {
  if (!arr.includes(key)) {
    arr.push(enumOpti[key]);
  }
  return arr;
}, []);

let selectClip =Object.keys(enumClip).reduce((arr:Array<string>, key:any) => {
  if (!arr.includes(key)) {
    arr.push(enumClip[key]);
  }
  return arr;
}, []);



type Props={
register:any;setValue:Function;path:string;unregister:Function
}

export const SwitchOptimizer:React.FC<Props> = ({register ,setValue,path,unregister}) => {

    const [opti,setOptimizer]=useState<string>("Adam");

 
    return ( 
               <div >
                <label>Optimizers:</label>
                <select onChange={(e:React.ChangeEvent<HTMLInputElement>)=>{unregister(`${path}`);setValue(`${path}.class_name`,e.target.value);setOptimizer(e.target.value)}}  defaultValue="Adam"> {//si pas de setValue => {...register("jjgfdijg")} pas de re-render , le onchange ne proc pas
                }
                {selectOpti.map((value:string,key:any)=>{return(<option key={key}>{value}</option>)})
                }
                </select>
               {/*<label>Learning Rate:</label>
               < input defaultValue="0.001"{...register(`${path}.config.learning_rate`)}/>*/} {// BUG :passer le register supprime la var par defaut de class_name
                
               }
                
               {/*<label>Keyword : </label>
                <select defaultValue=""> {//...register("training_config.optimizer_config")jsp
                }
                {selectClip.map((value:string,key:any)=>{return(<option key={key}>{value}</option>)})}
              </select>*/}

                <GenericInput register={register} key ={opti} path={`${path}.config`} classe={opti} pathDefaultM="./defaultOptimizers"/>

                </div>
          
         )



    
}


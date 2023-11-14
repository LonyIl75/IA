//@ts-nocheck
import { registerBackend } from '@tensorflow/tfjs-core'
import React ,{useState,useEffect} from 'react'
import {SelectProps} from "./../../Training"
import DataSet from "./../../Data/Dataset"


     const Error= {
   
        color: "#bf1650",
     
        display: "inline",
        content: "⚠ ",
        
            
        
    }
     


export const InputHeader:React.FC<SelectProps> = ({register,path,setValue,unregister,errors}) => {

    const [dataset,setDataset]= useState<string>("MNIST");


     useEffect(() => {
            unregister(`${path}`);
            setValue(`${path}.dataset_config.class_name`,dataset);
            setValue(`${path}.dataset_config`,DataSet[dataset]);
  },[dataset]);



    return (
        <>
             <div>
            <label>Epoch:</label>
            <input type="text" {...register(`${path}.custom_param.epoch`)}/>
            {
            errors?.input_header?.custom_param?.epoch && <p style={Error} >{errors?.input_header?.custom_param?.epoch?.message}</p>
            }
            </div>
            <div>
            <label>Batch Size:</label>
            <input type ="text" {...register(`${path}.custom_param.batch_size`)}/>
              {
            errors?.input_header?.custom_param?.batch_size && <p style={Error}>{errors?.input_header?.custom_param?.batch_size?.message}</p>
            }
            </div>
            <div>
            <label>DataSet:</label>
            <select onChange={(e:React.ChangeEvent<HTMLInputElement>)=>{setDataset(e.target.value)}} value={dataset}  > 
                {Object.keys(DataSet).map((value:string,key:any)=>{return(<option key={key}>{value}</option>)})
                }
            </select>
              {
            //errors?.input_header?.dataset && <p style={Error}>{errors?.input_header?.dataset?.message}</p>
            }  
            </div> 


        </>
    )
}

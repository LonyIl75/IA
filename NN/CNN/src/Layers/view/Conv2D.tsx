import React, { forwardRef, useState, useEffect } from "react";
import ReactDOM from "react-dom";
import { useForm, Controller, useFieldArray } from "react-hook-form";
import { Checkbox ,MenuItem,Select} from "@material-ui/core";
import { getConstantValue } from "typescript";
import {PropsLayer} from "../Props/interfaceProps"
import {ShapeInput} from "./../../help/ShapeInput"

import defaultLayers from "./../Data/defaultLayers"

export  const Conv2D:React.FC<PropsLayer> = ({register,path,getValue,setValue}) => {
    
  
console.log("conv")
  

   setValue(`${path}`,defaultLayers["Conv2D"])
    setValue(`${path}`,getValue(`${path}.config.filters`))
    console.log(path)
        //const {ref , ...field}=register(`${path}.config.test`)
    return (
        <div>
                 <h5 className="card-header">Conv2D</h5>
                  <div className="card-body border-bottom">
                    <div className="form-row">
      
       <div className="form-group col-md-2">
        <label>Activation:</label>
        <select {...register(`${path}.config.activation`)}>
         
         {/*<option value="get">get</option> */}
          <option value="">--Please choose an activation function--</option>
          <option value="linear">linear</option>
          <option value="relu">relu</option>
          <option value="sigmoid">sigmoid</option>
          <option value="softmax">softmax</option>
          <option value="tanh">tanh</option>
        </select>
      </div>
       
       <div className="form-group col-md-2">
         <label>Dilatation Rate:</label>
     <ShapeInput setValue={setValue} getValue={getValue} path={`${path}.config.dilatation_rate`} defaultValue={"1,1"} /></div>

      </div>
       <div className="form-group col-md-2">
          <label >dtype:</label>
          <select {...register(`${path}.config.dtype`)}   >
               <option value="float32" >float32</option>
                 <option value="float64"  >float64</option>
                  <option value="mixed_float16"  >mixed_float16</option>
                   <option value="mixed_bfloat16" >mixed_bfloat16</option>
            </select>
             </div>
        
        <div className="form-group col-md-2">
        <label >Filters:</label>
        <input onBlur={(e)=>setValue(`${path}.config.filters`,parseInt(e.target.value,10))}  type="number"  defaultValue={12}/></div>
       
      <div className="form-group col-md-2">
        <label >Kernel_size:</label>
        <ShapeInput setValue={setValue} getValue={getValue} path={`${path}.config.kernel_size`} defaultValue={"2,2"} readonly /></div>

        <div className="form-group col-md-2">
        <label >Name:</label>
        <input {...register(`${path}.config.name`)} type="text"/></div>
 
        <div className="form-group col-md-2">
        <label >Padding:</label>
          <select {...register(`${path}.config.padding`)}>
                   <option value="valid" >valid</option>
                    <option value="same" >same</option>
          </select>
          </div >

              <div className="form-group col-md-2">
                           <label >Strides:</label>
                            <ShapeInput setValue={setValue} getValue={getValue} path={`${path}.config.strides`} defaultValue={"1,1"} />{//placeholder="{this.state.pool_size}"
                             }
                           </div>

                        <div className="form-group col-md-2">
                             <label >Trainable:</label>
                          <input type="checkbox" {...register(`${path}.config.trainable`)} />
                          
                           </div>

                         </div>
      
       </div>
                     


    )
}

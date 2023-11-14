//@ts-nocheck
import React, { forwardRef, useState, useEffect } from "react";
import ReactDOM from "react-dom";
import { useForm, Controller, useFieldArray } from "react-hook-form";
import { Checkbox ,MenuItem,Select} from "@material-ui/core";
import { getConstantValue } from "typescript";
import {PropsLayer} from "../Props/interfaceProps"

import {ShapeInput} from"./../../help/ShapeInput"

import defaultLayers from "./../Data/defaultLayers"

export  const Dense:React.FC<PropsLayer> = ({register,path,setValue,getValue}) => {

setValue(`${path}`,defaultLayers["Dense"])

     return (
        <div>
           <h5 className="card-header">Dense</h5>
                  <div className="card-body border-bottom">
                    <div className="form-row">
      
       <div className="form-group col-md-2">
        <label >Activation:</label>
        <select {...register(`${path}.config.activation`)}>
          <option value="">--Please choose an activation function--</option>
         {/* <option value="get">get</option>*/}
          <option value="linear">linear</option>
          <option value="relu">relu</option>
          <option value="sigmoid">sigmoid</option>
          <option value="softmax">softmax</option>
          <option value="tanh">tanh</option>
        </select>
      </div>
       <div className="form-group col-md-2">
           <label >dilatation_rate:</label>
         <ShapeInput setValue={setValue} getValue={getValue} path={`${path}.config.dilatation_rate`} defaultValue={"1,1"} /></div>
       <div className="form-group col-md-2">
          <label >dtype:</label>
          <select {...register(`${path}.config.dtype`)} >
               <option value="float32" >float32</option>
                 <option value="float64"  >float64</option>
                  <option value="mixed_float16"  >mixed_float16</option>
                   <option value="mixed_bfloat16" >mixed_bfloat16</option>
            </select>
             </div>

        <div className="form-group col-md-2">
        <label >Name:</label>
        <input {...register(`${path}.name`)} type="text" /></div>

                             <div className="form-group col-md-2">
                          <input type="checkbox" {...register(`${path}.config.trainable`)}/>
                           <label >Trainable:</label>
                           </div>

        <div className="form-group col-md-2">
        <label >Units:</label>
        <input onBlur={(e)=>setValue(`${path}.config.units`,parseInt(e.target.value,10))}  type="number" pattern="\d+" required/></div>
        </div>
        </div>
        </div>
    )
}

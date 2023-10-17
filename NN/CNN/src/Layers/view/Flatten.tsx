import React, { forwardRef, useState, useEffect } from "react";
import ReactDOM from "react-dom";
import { useForm, Controller, useFieldArray } from "react-hook-form";
import { Checkbox ,MenuItem,Select} from "@material-ui/core";
import { getConstantValue } from "typescript";
import {PropsLayer} from "../Props/interfaceProps"
import {ShapeInput} from "./../../help/ShapeInput"

import defaultLayers from "./../Data/defaultLayers"

export  const Flatten:React.FC<PropsLayer> = ({register,path,setValue,getValue}) => {

  setValue(`${path}`,defaultLayers["Flatten"])
    
  return (
         <div className="card">
                <h5 className="card-header">Flatten</h5>
                  <div className="card-body border-bottom">
                    <div className="form-row">
                      <div className="form-group col-md-2">
                           <label >Rate:</label>
                           <ShapeInput type ="text" /*pattern="/^(0(\.\d+)?|1(\.0+)?)$/"*/ getValue={getValue} path={`${path}.config.rate`} defaultValue={"1,1"}setValue={setValue}  />
                        </div>

                        <div className="form-group col-md-2">
                           <label >dtype:</label>
                             <select {...register(`${path}.config.dtype`)} >
                             <option value="float32"  >float32</option>
                             <option value="float64"  >float64</option>
                             <option value="mixed_float16"  >mixed_float16</option>
                             <option value="mixed_bfloat16" >mixed_bfloat16</option>
                           </select>
                           </div>
                         <div className="form-group col-md-2">

                           <label >Name</label>
                           <input type ="text"{...register(`${path}.name`)} />
                        </div>
                         <div className="form-group col-md-2">
                        <label >Trainable:</label>
                          <input type="checkbox" {...register(`${path}.config.trainable`)} />
                          
                           </div>

          
                        
                      </div>

                    </div>
                  </div>
    )
}


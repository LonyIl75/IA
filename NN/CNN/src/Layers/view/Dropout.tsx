import React, { forwardRef, useState, useEffect } from "react";
import ReactDOM from "react-dom";
import { useForm, Controller, useFieldArray } from "react-hook-form";
import { Checkbox ,MenuItem,Select} from "@material-ui/core";
import { getConstantValue } from "typescript";
import {PropsLayer} from "../Props/interfaceProps"

import {ShapeInput} from "./../../help/ShapeInput"

import defaultLayers from "./../Data/defaultLayers"

export  const Dropout:React.FC<PropsLayer> = ({register,path,setValue,getValue}) => {


  console.log(defaultLayers["Dropout"])

  setValue(`${path}`,defaultLayers["Dropout"])
    
    return (
        <div className="card">
                <h5 className="card-header">Dropout</h5>
                  <div className="card-body border-bottom">
                    <div className="form-row">
                      <div className="form-group col-md-2">
                           <label> Rate:</label>
                             <input type ="number" /*pattern="/^(0(\.\d+)?|1(\.0+)?)$/"*/ onBlur={(e)=>setValue(`${path}.config.rate`,parseInt(e.target.value,10))}  
                              defaultValue={0.1} />
                      </div>
                       <div className="form-group col-md-2">
                           <label >Noise_shape:</label>
                              <ShapeInput setValue={setValue} getValue={getValue} path={`${path}.config.noise_shape`} defaultValue={"null,28,28,3"} /*pattern="\((\d),\1+\)"*/ />
                           </div>

                         <div className="form-group col-md-2">

                           <label >Name</label>
                           <input type ="text" {...register(`${path}.name`)} />
                        </div >
                      
                        
                      </div>

                    </div>
                  </div>
    )
}

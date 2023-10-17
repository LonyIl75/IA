import React,{useState,useEffect} from "react";
import { useHistory } from "react-router-dom";
import { useModels } from "../Provider/ModelsContext";
import db from '../Provider/db'
import ModalImage from "react-modal-image";
import * as tfvis from "@tensorflow/tfjs-vis"
import { summarize } from "../Model/SequentialModel";
import { append } from "@bokeh/bokehjs/build/js/types/core/dom";
import { deepMap } from 'react-children-utilities';






const ViewModel=({id,body}:{id:number,body:any})=>{

  
 const layerstoString = body.config.layers.map((elem)=>elem.class_name.concat(' | '))
  return(
    <>
    <td>{id}</td>
    <td>{body.name}</td>
    <td style={{ textOverflow: "ellipsis",whiteSpace: "nowrap",overflow: "hidden",width:"30%",maxWidth:"30%"}} >{layerstoString}</td>
    <td><button onClick={()=>summarize(body,id)}>Details</button></td>
 
    

  </>)

}

//use hook pour viewModel

const ContainerModels2 =({id,body,handleDelete,handleToggle,name_table}:{id:number,name_table:string,body:any,handleDelete:Function,handleToggle:Function})=>{
 
return(
  <>
    <ViewModel id={id} body={body} />
    <td><input type="checkbox" onClick={()=>handleToggle(id,body.name)}/></td>
    <td><button onClick={()=>handleDelete(id,name_table)}>rm</button></td>
    
  </>)

}
const ContainerWaitingModels =({id,body,handleAbort,name_table}:{id:number,name_table:string,body:any,handleAbort:Function})=>{
 /* <td>Avance_bar</td> {//barre de progression du fetch/promesse + date debut ect 
    }*/
return(
  <>
  
    <ViewModel id={id} body={body} />
    
    
    <td><button onClick={()=>handleAbort(id,name_table)}>Cancel</button></td>
   
    
  </>)

}


export const Repertoire = () => {

 

    const {getModel,appendModel,models,removeModel}= useModels()
    
    const history = useHistory();
 

    //A changer: 
    const [lesModels,setModels]=useState<any>([])
    const [lesWaitingModels,setWaitingModels]=useState<any>([])

    useEffect(()=>{

    db.table("Models2").toArray().then((db_models)=>{setModels(db_models);})
    db.table("waitingModels").toArray().then((db_wmodels)=>{setWaitingModels(db_wmodels);})
    //window.location.reload()
  },[models])


  const handleDelete = (id:number,table_name:string)=>
    {tfvis.visor().close(); db.table(table_name)
          .delete(id)

      }
  
  const handleToggle = (id:number,rest:string="")=>{
 
    if(getModel(id)===null)appendModel({id:id,description:rest})
    else removeModel(id)
    
  }

  const handleAbort=(id:number,table_name:string)=>{
      tfvis.visor().close(); db.table(table_name)
          .delete(id) // abort a la place de delete vue que tableau de promesse fetch
          


  }
  // PROMISE DEXIE:    console.log(db.table("Models2").each((elem)=>console.log( elem)))
  //ARRAY OF : console.log(lesModels.map((model)=>console.log( model)))
    return (
    <div>
      <div>
  <h2>Modele Appris :</h2>
      <table style={{tableLayout: "fixed",width:"100%"}}>

    <thead>
        
   <tr>
  <th scope="col" style={{width:"30%"}} >ID</th>
  <th scope="col"style={{width:"30%"}} >NameModel</th>
  <th scope="col">Content</th>
</tr>
    </thead>
    
 
  <tbody>
  {lesModels.map((model:any) => { 
  //=> <TableRow>
  return(<tr>
   
    <ContainerModels2
    id={model.id}//envoyer un objet json {table_name:"Models2",item:{key:model.id,body:{...model}}}
    {...model}
    name_table={"Models2"}
    key={model.id}
    handleToggle={handleToggle}
    handleDelete={handleDelete}/>
    </tr>)
    //</TableRow>)
  })
}
   </tbody>
</table>
</div>

<div>
  <h2>Model en cours d'apprentissage :</h2>
{lesWaitingModels.length?
<table style={{tableLayout: "fixed",width:"50%"}}>
  
    <thead>   
   <tr>
  <th scope="col" style={{width:"30%"}} >ID</th>
  <th scope="col"style={{width:"30%"}} >NameModel</th>
  <th scope="col">Content</th>
 {//<th scope="col">Progression</th>
}
</tr>  

</thead>


<tbody>

{lesWaitingModels.map((wmodel:any) => { 
  //=> <TableRow>
  return(<tr>
   
    <ContainerWaitingModels
    id={wmodel.id}//envoyer un objet json {table_name:"Models2",item:{key:model.id,body:{...model}}}
    {...wmodel}
    key={wmodel.id}
    name_table={"waitingModels"}
    handleAbort={handleAbort}/>
  
    </tr>)
    //</TableRow>)
  })
}
    </tbody>
</table>
:<p>Aucun modele n'est en cours d'apprentissage</p>}
</div>

<button onClick={()=>history.push("./Training",8000)}>Allez a l'entrainement</button>
</div>
    )
}


/*const ViewModel=({id,body}:{id:number,body:any})=>{
 // console.log(body.config.layers)
  //const[isSelected,setSelected]=useState(false)
  const toggle = ()=>{setSelected(!isSelected)}
  useEffect(()=>{
    if(isSelected)console.log("ajoute ")
 // },[isSelected])
//<td style={{ textOverflow: "ellipsis",whiteSpace: "nowrap",overflow: "hidden",width:"30%",maxWidth:"30%"}} ></td>
 const layerstoString = body.config.layers.map((elem)=>elem.class_name.concat(' | '))
  return(
    <>
    <span>{id}</span>
    <span>{body.name}</span>
    <span>{layerstoString}</span>
    <button onClick={()=>summarize(body,id)}>Details</button>
   // { <ModalImage
  small={"./eyes.jpg"}
  large={"./logo192.png"}
  alt="Hello World!"
   // />}
    

  </>)

}*/


/*
stackoverflow : https://stackoverflow.com/questions/32916786/react-children-map-recursively 
   const recursiveMap= (children:any)=>  {
     console.log(children)  ;
       React.Children.map(children, child => {
    if (!React.isValidElement(child)) {
      return <td style={{ textOverflow: "ellipsis",whiteSpace: "nowrap",overflow: "hidden",maxWidth:"30%"}} > {child}</td>;
    }
    if (child.props.children) {
      child = React.cloneElement(child, {
        children: recursiveMap(child.props.children)
      });
    }
})}*/

/*
TODO : recursive children but ON PLAIN COMPONENT : <compo>(contenant :<div>....</div>)+ change tag name and switch case props ,PENIBLE 
const TableRow =({children}:{children:any})=> {
    const childrenArray = React.Children.toArray(children)
    return(
      <tr>
      {deepMap(children, (child: ReactNode) => {
      console.log(child)
      return (//<td style={{ textOverflow: "ellipsis",whiteSpace: "nowrap",overflow: "hidden",width:"30%",maxWidth:"30%"}} >{child}</td>)
      })}
  

      </tr>

    )

}*/




/*
    function viewModels() {
            let arr = [{}]
            const tx = db.transaction("Models","readonly")
            const pNotes = tx.objectStore("Models")
            const request = pNotes.openCursor()
            request.onsuccess = e => {

                const cursor = e.target.result

                if (cursor) {
                    arr.push({id: cursor.key, Model_config: cursor.value.body })
                    cursor.continue()
                }
            }
            return arr;
        }
*/

//@ts-nocheck
import React, { createContext, useState, useContext ,useEffect} from 'react'
import {IModelID,ModelsContext} from './ModelsContext' 
import db from './db'


export const ModelsProvider = ({children}:{children:any}) => {


  const [models, setModels] = useState<Array<IModelID>>([]);
//PEUT ETRE MIEUX que de restreindre a selectModelVisu


//BULK DELETE or ASYNC and refresh quand la sequence de delete est assuré d'être fini (10 sec sans faire une op )
useEffect(()=>{
 db.on('changes', function (changes) {
      changes.forEach(function (change) {
        switch (change.type) {
          case 1: // CREATED
            console.log('An object was created: ');
            break;
          case 2: // UPDATED
            console.log('An object with key ');
            window.location.reload()
            break;
          case 3: // DELETED
            console.log('An object was deleted: ');
            window.location.reload()
            break;
        }
      })
     
    
    })
  },[])

  const getModels=():Array<IModelID>=>{return models}

  const appendModel = (new_model:IModelID) : void => {
    console.log("append")
    console.log(new_model.id)
    if(getModel(new_model.id)===null){
    setModels((prevModels:any ) => { //IModelID
       console.log("prev"+prevModels);
      return prevModels?[...prevModels,new_model]:[new_model]}
    )
    }
    else console.log(`${new_model.id} deja present`)
  }
  const getModel= (id:number):IModelID|null =>{ //a changer
    console.log(id);
    console.log(models)
    //models.map((item)=>console.log(item.id))
    let model=models.find((item:IModelID)=>item.id==id);
    return model?model:null;

  }

    const removeModel = (id: number) :IModelID|null => {
    const pop = getModel(id);
    setModels(models!.filter((item: IModelID) => item.id !== id))
    return pop ;
    }

const resetModels = ()=>{setModels([])} //default


  return <ModelsContext.Provider value={{models,getModel,getModels,setModels, appendModel,removeModel ,resetModels }}>
    {children}
  </ModelsContext.Provider>
}


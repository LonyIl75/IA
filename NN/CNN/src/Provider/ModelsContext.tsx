
import { createContext, useContext } from 'react'

export interface IModelID{
    id:number;
    description:string;
}
// export type IPartialModelID = Partial<IModelID>  puis import IPartialModelID as IModelID :fear 

export type ModelsContextType = { //todo: virtual  #ici
    models:Array<IModelID>;
    getModels:()=>Array<IModelID>;
    getModel:(id:number)=>IModelID|null;
    setModels:(_models:Array<IModelID>) => void;
    appendModel:(model:IModelID)=>void;
    removeModel:(id:number)=> IModelID|null ;//TODO:return pop element 
    resetModels:()=>void;
}

export const ModelsContext = createContext<ModelsContextType>({}); //ici:

export const useModels = () => useContext(ModelsContext)
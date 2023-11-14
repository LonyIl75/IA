//@ts-nocheck
import * as Yup from "yup"
import * as help from "./../../help/IntefaceUtil"
import * as help_verif from "./../../help/verificationUtil"
import {activations} from"./../Interface/Layers"
//TODO : Rajouter les types du fichier ActivationLayer



const SchemasInitializers={
 Constant:Yup.object({}),
 GlorotNormal:Yup.object({}),
 GlorotUniform:Yup.object({}),
 HeNormal:Yup.object({}),
 HeUniform:Yup.object({}),
 Identity:Yup.object({}),
 LecunNormal:Yup.object({}),
 LecunUniform:Yup.object({}),
 Ones:Yup.object({}),
 Orthogonal:Yup.object({}),
 RandomNormal:Yup.object({}),
 RandomUniform:Yup.object({}),
 TruncatedNormal:Yup.object({}),
 VarianceScaling:Yup.object({}),
 Zeros:Yup.object({}),
 constant:Yup.object({}),
 glorot_normal:Yup.object({}),
 glorot_uniform:Yup.object({}),
 he_normal:Yup.object({}),
 he_uniform:Yup.object({}),
 identity:Yup.object({}),
 lecun_normal:Yup.object({}),
 lecun_uniform:Yup.object({}),
 ones:Yup.object({}),
 orthogonal:Yup.object({}),
 random_normal:Yup.object({}),
 random_uniform:Yup.object({}),
 truncated_normal:Yup.object({}),
 variance_scaling:Yup.object({}),
 zeros:Yup.object({}),
}

const SchemasRegularizers ={
 L1:Yup.object({}),
 L1L2:Yup.object({}),
 L2:Yup.object({}),
 l1:Yup.object({}),
 l2:Yup.object({}),
}

const SchemasConstraints={
 MaxNorm:Yup.object({}),
 MinMaxNorm:Yup.object({}),
 NonNeg:Yup.object({}),
 RadialConstraint:Yup.object({}),
 UnitNorm:Yup.object({}),
 max_norm:Yup.object({}),
 min_max_norm:Yup.object({}),
 non_neg:Yup.object({}),
 radial_constraint:Yup.object({}),
 unit_norm:Yup.object({}),
}




export const ConfigLayer =Yup.object({
    trainable:Yup.boolean().default(true),
    dtype:Yup.mixed().oneOf(help.dtype) .default("float32"),
    dynamic:Yup.boolean().default(false),
}).concat(help_verif.Module)


const ActivationLayer = Yup.lazy( (value:any) =>{
      

    return Yup.object({
    
     
    activation: Yup.mixed().oneOf(activations).default("linear"),
    activity_regularizer:help_verif.generic({schema_collection:SchemasRegularizers,...value["activity_regularizer"]}).nullable().default(null),
    kernel_initializer :help_verif.generic({schema_collection:SchemasInitializers,...value["kernel_initializer"]}).nullable().default(Yup.object({class_name:Yup.mixed().default("GlorotUniform"),config: SchemasInitializers["GlorotUniform"]})),
    kernel_constraint:help_verif.generic({schema_collection:SchemasConstraints,...value["kernel_constraint"]}).nullable().default(null),
    kernel_regularizer:help_verif.generic({schema_collection:SchemasRegularizers,...value["kernel_regularizer"]}).nullable().default(null),
    use_bias:Yup.boolean(),
    bias_initializer:help_verif.generic({schema_collection:SchemasInitializers,...value["bias_initializer"]}).nullable().default(Yup.object({class_name:Yup.mixed().default("zeros"),config: SchemasInitializers["zeros"]})),
    bias_constraint:help_verif.generic({schema_collection:SchemasConstraints,...value["bias_constraint"]}).nullable().default(null),
    bias_regularizer:help_verif.generic({schema_collection:SchemasRegularizers,...value["bias_regularizer"]}).nullable().default(null),
    

    }).concat(ConfigLayer)
  
})


export const ConfigDropout =Yup.lazy( (value:any) =>{
  console.log(value)
  return Yup.object({
  rate:Yup.number().test({
      message:"${path} must be between 0 and 1 ",
    test: (val:any) =>{ return val>=0 && val <=1 }}).required(),
  noise_shape: help_verif.inputShape().required()
  }).concat(ConfigLayer)

})
export const ConfigConv2D =Yup.lazy( (value:any) =>{
  return Yup.object({
    activation: Yup.mixed().oneOf(activations),
    activity_regularizer:help_verif.generic({schema_collection:SchemasRegularizers,...value["activity_regularizer"]}).nullable().default(null),
    kernel_initializer :help_verif.generic({schema_collection:SchemasInitializers,...value["kernel_initializer"]}).nullable().default(Yup.object({class_name:Yup.mixed().default("GlorotUniform"),config: SchemasInitializers["GlorotUniform"]})),
    kernel_constraint:help_verif.generic({schema_collection:SchemasConstraints,...value["kernel_constraint"]}).nullable().default(null),
    kernel_regularizer:help_verif.generic({schema_collection:SchemasRegularizers,...value["kernel_regularizer"]}).nullable().default(null),
    use_bias:Yup.boolean().default(true),
    bias_initializer:help_verif.generic({schema_collection:SchemasInitializers,...value["bias_initializer"]}).nullable().default(Yup.object({class_name:Yup.mixed().default("zeros"),config: SchemasInitializers["zeros"]})),
    bias_constraint:help_verif.generic({schema_collection:SchemasConstraints,...value["bias_constraint"]}).nullable().default(null),
    bias_regularizer:help_verif.generic({schema_collection:SchemasRegularizers,...value["bias_regularizer"]}).nullable().default(null),
 
   filters:Yup.number().positive().integer().required(),
   kernel_size:help_verif.tuple2().required(),
   groups:Yup.number().positive().integer(),
   dilatation_rate:help_verif.tuple2(),
   padding:Yup.mixed().oneOf(help.padding).default('valid'),
   strides:help_verif.tuple2().default([1,1]).test({
      message: '${path}!=1 test: ${path}',
      test: function (strides:any) {
          return (strides!==1 && strides !== [1,1]) && (this.parent.dilatation_rate!==1 && this.parent.dilatation_rate !== [1,1])          
      },
    }),
 
  }).concat(help_verif.Input).concat(ConfigLayer)

})

export const ConfigDense=Yup.lazy( (value:any) =>{
   return Yup.object({
    activation: Yup.mixed().oneOf(activations).default("linear"),
    activity_regularizer:help_verif.generic({schema_collection:SchemasRegularizers,...value["activity_regularizer"]}).nullable().default(null),
    kernel_initializer :help_verif.generic({schema_collection:SchemasInitializers,...value["kernel_initializer"]}).nullable().default(Yup.object({class_name:Yup.mixed().default("GlorotUniform"),config: SchemasInitializers["GlorotUniform"]})),
    kernel_constraint:help_verif.generic({schema_collection:SchemasConstraints,...value["kernel_constraint"]}).nullable().default(null),
    kernel_regularizer:help_verif.generic({schema_collection:SchemasRegularizers,...value["kernel_regularizer"]}).nullable().default(null),
    use_bias:Yup.boolean().default(true),
    bias_initializer:help_verif.generic({schema_collection:SchemasInitializers,...value["bias_initializer"]}).nullable().default(Yup.object({class_name:Yup.mixed().default("zeros"),config: SchemasInitializers["zeros"]})),
    bias_constraint:help_verif.generic({schema_collection:SchemasConstraints,...value["bias_constraint"]}).nullable().default(null),
    bias_regularizer:help_verif.generic({schema_collection:SchemasRegularizers,...value["bias_regularizer"]}).nullable().default(null),
    units:Yup.number().positive().integer().required()

  }).concat(ConfigLayer)
})

export const ConfigFlatten = Yup.object({}).concat(help_verif.Input).concat(ConfigLayer)

export const ConfigMaxPooling2D  =Yup.lazy( (value:any) =>{ return Yup.object({
 pool_size: help_verif.tuple2().default([2,2]),
 strides: help_verif.tuple2(),
 padding:Yup.mixed().oneOf(help.padding).default("valid")
}).concat(ConfigLayer)})

export const  ConfigInput  = Yup.object({
    batch_input_shape:help_verif.inputShape(),
    sparse:Yup.boolean().default(false),
    ragged:Yup.boolean().default(false)

}).concat(ConfigLayer)
 const InputLayer =Yup.lazy((value:any):any =>{return help_verif.generic({schema_collect:SchemasLayers,...value["InputLayer"]})})
 const Dropout =Yup.lazy((value:any):any =>{return help_verif.generic({schema_collect:SchemasLayers,...value["Dropout"]})})
 const Conv2D =Yup.lazy((value:any):any =>{return help_verif.generic({schema_collect:SchemasLayers,...value["Conv2D"]})})
 const Dense =Yup.lazy((value:any):any =>{return help_verif.generic({schema_collect:SchemasLayers,...value["Dense"]})})
 const Flatten =Yup.lazy((value:any):any =>{return help_verif.generic({schema_collect:SchemasLayers,...value["Flatten"]})})
 const MaxPooling2D =Yup.lazy((value:any):any =>{return help_verif.generic({schema_collect:SchemasLayers,...value["MaxPooling2D"]})})


 export const SchemasLayers ={
    InputLayer:ConfigInput,
    Conv2D:ConfigConv2D,
    MaxPooling2D:ConfigMaxPooling2D,
    Dropout:ConfigDropout,
    Flatten:ConfigFlatten,
    Dense:ConfigDense,

}





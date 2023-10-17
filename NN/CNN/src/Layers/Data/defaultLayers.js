export default {
    Conv2D:{
    class_name:"Conv2D",
    config:{
    filters:12, kernel_size:[2,2], strides:[1, 1], padding:'valid',
    data_format:"channels_first", dilatation_rate:[1, 1], groups:1, activation:null,
    use_bias:true, kernel_initializer:'GlorotUniform',
    bias_initializer:'zeros', kernel_regularizer:null,
    bias_regularizer:null, activity_regularizer:null, kernel_constraint:null,
    bias_constraint:null ,name:"Conv2D"
}
    },

   MaxPooling2D:{
       class_name:"MaxPooling2D",
    config:{
    pool_size:[2, 2], strides:[1, 1], padding:'valid', data_format:"channels_first",
    name:"MaxPooling2D"
    }
    
},

InputLayer:{
    class_name:"InputLayer",
    config:{
    batch_input_shape:[null,28,28,3],sparse:false,
    name:"InputLayer", ragged:false
    }
},
Dropout:{
    class_name:"Dropout",
    config:{
    rate:0.1, noise_shape:[null,28,28,3],
    name:"Dropout"
    }
},
Flatten:{
    class_name:"Flatten",
    config:{
    data_format:"channels_first" ,
    name:"Flatten"
    }
},

Dense:{
    class_name:"Dense",
    config:{
    units:42, activation:null, use_bias:true,
    kernel_initializer:'GlorotUniform',
    bias_initializer:'zeros', kernel_regularizer:null,
    bias_regularizer:null, activity_regularizer:null, kernel_constraint:null,
    bias_constraint:null,
    name:"Dense"
    }
}


}
// pour le reste essayer de faire une requete get sur tensorflow doc  src: https://www.tensorflow.org/api_docs/python/tf/keras/layers?layer="Dense"
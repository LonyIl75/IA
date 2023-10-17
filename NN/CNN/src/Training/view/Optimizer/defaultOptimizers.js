
export default {

Adadelta:{
    learning_rate:0.001, rho:0.95, epsilon:1e-07, name:'Adadelta' 
    
},
Adagrad:{
    learning_rate:0.001, initial_accumulator_value:0.1, epsilon:1e-07,
    name:'Adagrad'  
},
Adam:{
    learning_rate:0.001, beta_1:0.9, beta_2:0.999, epsilon:1e-07, amsgrad:false,
    name:'Adam'  
},
Adamax:{
    learning_rate:0.001, beta_1:0.9, beta_2:0.999, epsilon:1e-07,
    name:'Adamax' 
},
Ftrl:{
    learning_rate:0.001, learning_rate_power:-0.5, initial_accumulator_value:0.1,
    l1_regularization_strength:0.0, l2_regularization_strength:0.0,
    name:'Ftrl' , l2_shrinkage_regularization_strength:0.0, beta:0.0,
    
},
Nadam:{
    learning_rate:0.001, beta_1:0.9, beta_2:0.999, epsilon:1e-07,
    name:'Nadam' 
},
RMSprop:{
    learning_rate:0.001, rho:0.9, momentum:0.0, epsilon:1e-07, centered:false,
    name:'RMSprop' 
},
SGD:{
    learning_rate:0.01, momentum:0.0, nesterov:false, name:'SGD' 
},



}
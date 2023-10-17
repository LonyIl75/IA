export default {
    
                class_name:"Sequential",
                name: "Sequential_model",
                expects_training_arg:true,
                batch_input_shape:[null,1,1,1],
                must_restore_from_config:true,
                config:{layers: [{}]},
                training_config:{
                loss: "mse", metrics: [[]], weighted_metrics: null,
                loss_weights: null,// run_eagerly:null,steps_per_execution:null
                optimizer_config: {class_name: 'RMSprop', config: {name: "RMSprop", learning_rate: 0.0010000000474974513, rho:0.9,centered:false, epsilon: 1e-07}},
                input_header:{epoch:1,batch_size:1}
            }
                }
            

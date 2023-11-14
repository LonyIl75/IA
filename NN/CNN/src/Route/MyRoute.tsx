//@ts-nocheck
import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import {CreateLayers} from "./../Model/CreateLayers";
import { Repertoire } from "./../Repertoire/Repertoire";
import{Training}from "./../Training/Training"

// import { Header } from "./components/Header";

export function MyRoute() {

  //  const { getModel} = useModels();
   
  return (
    <>
      <Router>
        <Switch>
          <Route exact path="/CreateLayers" component={CreateLayers} />
          <Route path="/Repertoire" component={Repertoire} />
          <Route path="/Training" component={Training} />
        </Switch>
      </Router>
    </>
  );
}

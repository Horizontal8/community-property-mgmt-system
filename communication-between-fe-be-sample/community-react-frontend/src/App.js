import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import UserComponent from './components/UserComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from "./components/FooterComponent";
import CreateResidentComponent from "./components/CreateResidentComponent";
import UpdateResidentComponent from "./components/UpdateResidentComponent";

function App() {
  return (
    <div>
        <Router>
            <HeaderComponent />
                <div className="container">
                    <Switch>
                        <Route path = "/" exact component={UserComponent}></Route>
                        <Route path = "/residents" component={UserComponent}></Route>
                        <Route path = "/add-resident/:id" component={CreateResidentComponent}></Route>
                        {/*<Route path = "/update-resident/:id" component={UpdateResidentComponent}></Route>*/}
                    </Switch>
                </div>
        </Router>
    </div>
  );
}

export default App;

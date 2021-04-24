import React, {Component} from 'react';
import '../styles/App.css';
import TopBar from './TopBar';
import Main from './Main';

import { TOKEN_KEY } from '../constants';

class App extends React.Component{

    state = {
        isLoggedIn: Boolean(localStorage.getItem(TOKEN_KEY)),
    }


    handleLoginSucceed = (token) => {
        console.log('token --- ', token)
        localStorage.setItem(TOKEN_KEY, token)
        this.setState({ isLoggedIn: true });
    }

    handleLogout = () => {
        localStorage.removeItem(TOKEN_KEY);
        this.setState({ isLoggedIn: false });
    }

    render(){
        return (
            <div className="App">
                <TopBar handleLogout={this.handleLogout}
                        isLoggedIn={this.state.isLoggedIn}
                />
                <Main
                    handleLoginSucceed={this.handleLoginSucceed}
                    isLoggedIn={this.state.isLoggedIn}
                />
            </div>
        );
    }
}
/*
export function authHeader(){
    let user = JSON.parse(localStorage.getItem('user'));

    if (user && user.authenticatorData){
        console.log(user.authenticatorData);
        return {'Authorization':'Basic ' + user.authenticatorData};
    } else {
        return {};
    }
}
*/
export default App;

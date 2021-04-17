import React, {Component} from 'react';

class HeaderComponent extends Component {
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="https://devexpress.github.io/devextreme-reactive/react/scheduler/demos/featured/remote-data/" className="navbar-brand">Community Mgmt App</a></div>
                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;
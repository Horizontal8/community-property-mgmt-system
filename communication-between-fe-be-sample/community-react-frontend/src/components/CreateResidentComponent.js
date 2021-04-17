import React, {Component} from 'react';
import UserService from "../services/UserService";

class CreateResidentComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            firstName: '',
            lastName: '',
            phone: '',
            preferredName: ''
        }

        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changePhoneHandler = this.changePhoneHandler.bind(this);
        this.changePreferredNameHandler = this.changePreferredNameHandler.bind(this);
        this.saveOrUpdateResident = this.saveOrUpdateResident.bind(this);
    }

    componentDidMount() {
        if(this.state.id == -1) {
            return
        } else{
            UserService.getUserById(this.state.id).then( (res) => {
                let resident = res.data;
                this.setState({firstName: resident.firstName,
                    lastName: resident.lastName,
                    phone: resident.phone,
                    preferredName: resident.preferredName})
            });
        }
    };

    changeFirstNameHandler = (event) => {
        this.setState({firstName: event.target.value});
    }

    changeLastNameHandler = (event) => {
        this.setState({lastName: event.target.value});
    }

    changePhoneHandler = (event) => {
        this.setState({phone: event.target.value});
    }

    changePreferredNameHandler = (event) => {
        this.setState({preferredName: event.target.value});
    }

    saveOrUpdateResident = (e) => {
        e.preventDefault();
        let resident = {firstName: this.state.firstName, lastName: this.state.lastName, phone: this.state.phone, preferredName: this.state.preferredName};
        console.log('resident => ' + JSON.stringify(resident))

        if(this.state.id == -1) {
            UserService.createUser(resident).then(res => {
                this.props.history.push('/residents');
            });
        } else{
            UserService.updateUser(resident, this.state.id).then( res => {
                this.props.history.push('/residents');
        });
        }
    }

    cancel() {
        this.props.history.push('/residents');
    }

    getTitle() {
        if(this.state.id == -1) {
            return <h3 className="text-center">Add Resident</h3>
        } else {
            return <h3 className="text-center">Update Resident</h3>
        }
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            {
                                this.getTitle()
                            }
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>First Name: </label>
                                        <input placeholder="First Name" name="firstName" className="form-control"
                                               value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Last Name: </label>
                                        <input placeholder="Last Name" name="firstName" className="form-control"
                                               value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Phone: </label>
                                        <input placeholder="Phone" name="phone" className="form-control"
                                               value={this.state.phone} onChange={this.changePhoneHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Preferred Name: </label>
                                        <input placeholder="Preferred Name" name="preferredName" className="form-control"
                                               value={this.state.preferredName} onChange={this.changePreferredNameHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveOrUpdateResident}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CreateResidentComponent;
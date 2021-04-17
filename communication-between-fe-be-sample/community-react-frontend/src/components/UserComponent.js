import React from 'react';
import UserService from "../services/UserService";

class UserComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            users:[]
        }
        this.addResident = this.addResident.bind(this);
        this.editResident = this.editResident.bind(this);
    }

    componentDidMount() {
        UserService.getUsers().then((response) => {
            this.setState({users: response.data})
        })
    }

    addResident() {
        this.props.history.push('/add-resident/-1');
    }

    editResident(id) {
        this.props.history.push(`/add-resident/${id}`);
    }

    render() {
        return (
            <div>
                <h1 className="text-center"> Residents List</h1>
                <div className="row">
                    <button className="btn btn-primary" onClick={this.addResident}> Add Resident</button>
                </div>
                <div className="row">
                    <table className="table table-striped">
                        <thead>
                            <tr>
                                <th> Resident First Name</th>
                                <th> Resident Last Name</th>
                                <th> Resident Phone</th>
                                <th> Resident Preferred Name</th>
                                <th> Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.users.map(
                                resident =>
                                    <tr key = {resident.id}>
                                        <td> {resident.firstName}</td>
                                        <td> {resident.lastName}</td>
                                        <td> {resident.phone}</td>
                                        <td> {resident.preferredName}</td>
                                        <td>
                                            <button onClick={ () => this.editResident(resident.id)} className="btn btn-info">Update </button>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default UserComponent;
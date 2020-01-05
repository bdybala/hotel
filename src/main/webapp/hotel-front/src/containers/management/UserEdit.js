import React, {Component} from 'react';
import {Button, FormControl, Glyphicon} from "react-bootstrap";

class UserEdit extends Component {

    constructor(props) {
        super(props);
        this.editUser = this.editUser.bind(this);

        this.firstNameChanged = this.firstNameChanged.bind(this);
        this.lastNameChanged = this.lastNameChanged.bind(this);
        this.emailChanged = this.emailChanged.bind(this);
        this.roleChanged = this.roleChanged.bind(this);

        this.state = {
            id: props.user.id,
            firstName: props.user.firstName,
            lastName: props.user.lastName,
            email: props.user.email,
            roleName: props.user.roleName
        };
    }

    render() {
        return (
            <tr>
                <th></th>
                <td>
                    <FormControl
                        type="text"
                        defaultValue={this.state.firstName}
                        placeholder="Jan"
                        onChange={this.firstNameChanged}
                    />
                </td>
                <td>
                    <FormControl
                        type="text"
                        defaultValue={this.state.lastName}
                        placeholder="Kowalski"
                        onChange={this.lastNameChanged}
                    />
                </td>
                <td>
                    <FormControl
                        type="email"
                        defaultValue={this.state.email}
                        placeholder="email@example.com"
                        onChange={this.emailChanged}
                    />
                </td>
                <td>
                    <FormControl componentClass="select" placeholder="Wybierz rolę"
                                 defaultValue={this.state.roleName} onChange={this.roleChanged}>
                        <option value='null' key='null'>---</option>
                        {this.props.roles.map(function (item) {
                            return <option key={item} value={item}>{item}</option>
                        })}
                    </FormControl>
                </td>
                <td>
                    <Button bsStyle="success" onClick={this.editUser}>
                        <Glyphicon glyph='glyphicon glyphicon-pencil'/>
                    </Button></td>
            </tr>)
    }

    firstNameChanged(e) {
        this.setState({firstName: e.target.value});
    }

    lastNameChanged(e) {
        this.setState({lastName: e.target.value});
    }

    emailChanged(e) {
        this.setState({email: e.target.value});
    }

    roleChanged(e) {
        this.setState({roleName: e.target.value});
    }

    editUser() {
        this.props.editUser(this.state);
    }
}


export default UserEdit;
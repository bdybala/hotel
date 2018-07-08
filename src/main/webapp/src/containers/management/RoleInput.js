import React, { Component } from 'react';

import { FormGroup, ControlLabel, FormControl, InputGroup, Glyphicon } from 'react-bootstrap';
import axios from 'axios';

const API = 'http://192.168.0.227:8080/api';
const FIND_ROLES = '/roles';

class RoleInput extends Component {
	constructor(props) {
		super(props)
		this.handleChange = this.handleChange.bind(this);
		this.state = {
			roles: [],
			isLoading: false,
		}
	}

	handleChange(e) {
		this.props.save(e.target.value);
	}

	getValidationState() {
		return this.props.validate('role');
	}

	render() {
		var listOfRoles = this.state.roles.map(function(item) {
			return <option key={item} value={item}>{item}</option>
		});

		return (
			<FormGroup controlId="formRoleInput" validationState={this.getValidationState()}>
				<ControlLabel>Wybierz rolę: </ControlLabel>
				<InputGroup>
					<InputGroup.Addon><Glyphicon glyph="glyphicon glyphicon-menu-hamburger" /></InputGroup.Addon>
					<FormControl componentClass="select" placeholder="Wybierz rolę" onChange={this.handleChange}>
						<option value='null' key='null'>---</option>
						{listOfRoles}
					</FormControl>
				</InputGroup>
			</FormGroup>
		);
	}

	showErrorModal(errorMessage) {
		this.props.showErrorModal(errorMessage);
	}

	componentDidMount() {
		this.setState({ isLoading: true })
		axios.get(API + FIND_ROLES)
		.then(response => this.setState({ roles: response.data, isLoading: false }))
		.catch(error => {
			this.setState({ isLoading: false });
			this.showErrorModal(error.message);
		})
	}
}

export default RoleInput;
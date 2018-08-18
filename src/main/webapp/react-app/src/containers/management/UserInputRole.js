import React, { Component } from 'react';

import { FormGroup, ControlLabel, FormControl, InputGroup, Glyphicon } from 'react-bootstrap';


class UserInputRole extends Component {
	constructor(props) {
		super(props)
		this.handleChange = this.handleChange.bind(this);
		this.state = {
			roles: [],
		}
	}

	handleChange(e) {
		this.props.save(e.target.value);
	}

	getValidationState() {
		return this.props.validate('role');
	}

	render() {
		var listOfRoles = this.props.roles.map(function(item) {
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

}

export default UserInputRole;
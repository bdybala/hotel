import React, { Component } from 'react';

import { FormGroup, ControlLabel, HelpBlock, FormControl, InputGroup } from 'react-bootstrap';

class UserInputFirstName extends Component {
	constructor(props) {
		super(props);
		this.handleChange = this.handleChange.bind(this);
	}

	getValidationState() {
		return this.props.validate('firstName');
	}

	handleChange(e) {
		this.props.save(e.target.value);
	}
	
	render() {
		return (
			<FormGroup 
				controlId="formFirstNameInput"
				validationState={this.getValidationState()}
			>
			<ControlLabel>Wpisz imię: </ControlLabel>
			<InputGroup>
				<InputGroup.Addon>Aa</InputGroup.Addon>
				{this.props.readOnly && <FormControl
						type="text"
						value={this.props.value}
						placeholder="Jan"
						onChange={this.handleChange}
						readOnly
				/>
				}
				{!this.props.readOnly && <FormControl
						type="text"
						value={this.props.value}
						placeholder="Jan"
						onChange={this.handleChange}
				/>
				}
				<FormControl.Feedback />
			</InputGroup>
			<HelpBlock>Imię powinno mieć conajmniej 3 znaki.</HelpBlock>
			</FormGroup>
		);
	}
}

export default UserInputFirstName;
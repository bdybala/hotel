import React, { Component } from 'react';

import { FormGroup, ControlLabel, HelpBlock, FormControl, InputGroup } from 'react-bootstrap';

class UserInputEmail extends Component {
	constructor(props) {
		super(props);
		this.handleChange = this.handleChange.bind(this);
	}

	getValidationState() {
		return this.props.validate('email');
	}

	handleChange(e) {
		this.props.save(e.target.value);
	}
	
	render() {
		return (
			<FormGroup controlId="formEmailInput" validationState={this.getValidationState()}>
				<ControlLabel>Wpisz e-mail: </ControlLabel>
				<InputGroup>
					<InputGroup.Addon>@</InputGroup.Addon>
					<FormControl
						type="email"
						value={this.props.value}
						placeholder="email@example.com"
						onChange={this.handleChange}
					/>
				</InputGroup>
				<FormControl.Feedback />
				<HelpBlock>Nie używaj polskich znaków ani znaków specjalnych (np. !#$%^&*)</HelpBlock>
			</FormGroup>
		);
	}
}

export default UserInputEmail;
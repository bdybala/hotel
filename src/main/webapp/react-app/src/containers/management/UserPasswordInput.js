import React, { Component } from 'react';

import { FormGroup, ControlLabel, HelpBlock, FormControl, InputGroup } from 'react-bootstrap';

class UserPasswordInput extends Component {
	constructor(props) {
		super(props);
		this.handleChange = this.handleChange.bind(this);
		this.state = {
			firstPassword: '',
			secondPassword: '',
		};
	}

	getValidationState(name) {
		switch(name) {
			case 'first':
				return this.props.validate('firstPassword');
			case 'second':
				return this.props.validate('secondPassword');
			default:
				return null;
		}
		
	}

	handleChange(e) {
		this.setState({ [e.target.name]: e.target.value });
		this.props.save(e.target.name, e.target.value);
	}
	
	render() {
		return (
			<div>
				<FormGroup 
					controlId="formFirstPasswordInput"
					validationState={this.getValidationState('first')}
				>
				<ControlLabel>Wpisz Hasło: </ControlLabel>
				<InputGroup>
					<InputGroup.Addon>**</InputGroup.Addon>
					<FormControl
						type="password"
						name="firstPassword"
						value={this.state.firstPassword}
						placeholder=" Hasło"
						onChange={this.handleChange}
					/>
					<FormControl.Feedback />
				</InputGroup>
				<HelpBlock>Hasło powinno mieć conajmniej 6 znaków.</HelpBlock>
				</FormGroup>
				
				<FormGroup 
					controlId="formSecondPasswordInput"
					validationState={this.getValidationState('second')}
				>
				<ControlLabel>Powtórz Hasło: </ControlLabel>
				<InputGroup>
					<InputGroup.Addon>**</InputGroup.Addon>
					<FormControl
						type="password"
						name="secondPassword"
						value={this.state.secondPassword}
						placeholder=" Powtórzone hasło"
						onChange={this.handleChange}
					/>
					<FormControl.Feedback />
				</InputGroup>
				<HelpBlock>Podane hasła muszą być takie same</HelpBlock>
				</FormGroup>
			</div>
		);
	}
}

export default UserPasswordInput;
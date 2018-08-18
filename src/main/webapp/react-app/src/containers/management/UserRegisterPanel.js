import React, { Component } from 'react';

import { Panel, Form, Glyphicon } from 'react-bootstrap';

import UserPasswordInput from './UserPasswordInput';
import UserFirstNameInput from './UserFirstNameInput';
import UserLastNameInput from './UserLastNameInput';
import UserEmailInput from './UserEmailInput';
import UserRoleInput from './UserRoleInput';

import './UserRegisterPanel.css';


class UserRegisterPanel extends Component {
	constructor(props, context) {
		super(props, context);

		this.setLastName = this.setLastName.bind(this);
		this.setFirstName = this.setFirstName.bind(this);
		this.setEmail = this.setEmail.bind(this);
		this.setPassword = this.setPassword.bind(this);
		this.setRole = this.setRole.bind(this);

		this.handleClick = this.handleClick.bind(this);
		this.getValidationState = this.getValidationState.bind(this);
		this.containsFalse = this.containsFalse.bind(this);

		this.validationArray = {
			lastName: '',
			firstName: '',
			email: '',
			firstPassword: '',
			secondPassword: '',
			role: '',
		}

		this.state = {
			lastName: '',
			firstName: '',
			email: '',
			firstPassword:'',
			secondPassword: '',
			role: '',
		};
	}

	getValidationState(field) {
		var result = null;
		switch(field) {
			case 'firstName':
				var length = this.state.firstName.length;
				if (length > 2) {
					this.validationArray.firstName = true;
					result = 'success';
					break;
				}
			    else if (length > 0) {
			    	this.validationArray.firstName = false;
			    	result = 'error';
			    	break;
			    }
			   	this.validationArray.firstName = false;
			    result = null;
			    break;
			case 'lastName':
				length = this.state.lastName.length;
				if (length > 2) {
					this.validationArray.lastName = true;
					result = 'success';
					break;
				}
			    else if (length > 0) {
			    	this.validationArray.lastName = false;
			    	result = 'error';
			    	break;
		    	}
		    	this.validationArray.lastName= false;
			    result = null;	
			    break;
			case 'email':
				const reg = /^[a-z\d]+[\w\d.-]*@(?:[a-z\d]+[a-z\d-]+\.){1,5}[a-z]{2,6}$/i;
				var value = this.state.email;
				let emailValid = value.match(reg);
				if (emailValid) {
					this.validationArray.email = true;
					result = 'success';
					break;
				}
			    else if (value.length > 0) {
			    	this.validationArray.email = false;
			    	result = 'error';
			    	break;
			    }
			    this.validationArray.email = false;
			    result = null;
			    break;
			case 'firstPassword':
				length = this.state.firstPassword.length;
				if (length > 5) {
					this.validationArray.firstPassword = true;
					result = 'success';
					break;
				}
			    else if (length > 0) {
			    	this.validationArray.firstPassword = false;
			    	result = 'error';
			    	break;
			    }
			    this.validationArray.firstPassword = false;
			    result = null;
			    break;
			case 'secondPassword':
				var passwordsMatch = this.state.firstPassword === this.state.secondPassword;
				length = this.state.secondPassword.length;
				if (length < 1) {
					this.validationArray.secondPassword = false;
					result = null;
					break;
				}
				else if (passwordsMatch) {
					this.validationArray.secondPassword = true;
					result = 'success';
					break;
				}
				this.validationArray.secondPassword = false;
				result = 'error';
				break;
			case 'role':
				if (this.state.role === '') {
					this.validationArray.role = false;
					result = null;
					break;
				}
				else if (this.state.role === 'null') {
					this.validationArray.role = false;
					result = 'error';
					break;
				}
				this.validationArray.role = true;
				result = 'success';
				break;
			default:
				result = 'warning';

		}
		return result;
	}

	setLastName(lastName) {
		this.setState({ lastName: lastName });
	}

	setFirstName(firstName) {
		this.setState({ firstName: firstName });
	}

	setEmail(email) {
		this.setState({ email: email });
	}

	setPassword(field, password) {
		if (field === 'firstPassword') {
			this.setState({ firstPassword: password });
		} else if (field === 'secondPassword') {
			this.setState({ secondPassword: password });
		}
	}

	setRole(role) {
		this.setState({ role: role });
	}

	handleClick() {
		if (this.containsFalse()) {
			console.log(this.state);
		} else {
			var newUser = {
				firstName: this.state.firstName,
				lastName: this.state.lastName,
				email: this.state.email,
				password: this.state.firstPassword,
				roleNameEnum: this.state.role
			};
			this.props.registerUser(newUser);
		}
	}

	containsFalse() {
		var containsFalse = false;
		for(var key in this.validationArray) {
			if (!this.validationArray[key]) { containsFalse = true;}
		}
		return containsFalse;
	}

	render() {
		return (
			<div className='UserRegisterPanel'>
				<Panel>
					<Panel.Heading>
					<Panel.Toggle>
						<Panel.Title className="panelTitle">
							<Glyphicon glyph="glyphicon glyphicon-collapse-down" />
							&nbsp;
							Rejestracja nowego użytkownika
						</Panel.Title>
					</Panel.Toggle>
					 </Panel.Heading>
						<Panel.Body collapsible>
							<Form>
								<UserFirstNameInput save={this.setFirstName} validate={this.getValidationState} />
								<UserLastNameInput save={this.setLastName} validate={this.getValidationState} />
								<UserEmailInput save={this.setEmail} validate={this.getValidationState} />
								<UserPasswordInput save={this.setPassword} validate={this.getValidationState} />
								<UserRoleInput 
									save={this.setRole} 
									validate={this.getValidationState} 
									showErrorModal={this.props.showErrorModal} 
									roles={this.props.roles} />
								<button type="button" className= "btn btn-primary" onClick={this.handleClick}>
									Dodaj
								</button>
							</Form>
						</Panel.Body>
				</Panel>
			</div>
		);
	}
}

export default UserRegisterPanel;
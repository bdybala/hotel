import React, { Component } from 'react';

import { Form, ControlLabel, FormControl, Glyphicon, Panel } from 'react-bootstrap';


class UserSearchPanel extends Component {
	constructor(props) {
		super(props);
		
		this.handleSearch = this.handleSearch.bind(this);
		this.handleFirstNameChange = this.handleFirstNameChange.bind(this);
		this.handleLastNameChange = this.handleLastNameChange.bind(this);
		this.handleEmailChange = this.handleEmailChange.bind(this);
		this.handleRoleChange = this.handleRoleChange.bind(this);
		this.showErrorModal = this.showErrorModal.bind(this);

		this.state = {
			roles: [],
			firstName: '',
			lastName: '',
			email: '',
			roleName: '',
		}
	}

	handleSearch() {
		let searchFields = [];
		if (this.state.firstName !== '') {
			searchFields.push({
				name: 'firstName',
				value: this.state.firstName,
			});
		}
		if (this.state.lastName !== '') {
			searchFields.push({
				name: 'lastName',
				value: this.state.lastName,
			});
		}
		if (this.state.email !== '') {
			searchFields.push({
				name: 'email',
				value: this.state.email,
			});
		}
		if (this.state.roleName !== '') {
			searchFields.push({
				name: 'roleName',
				value: this.state.roleName,
			});
		}
		this.props.updateSearchFields(searchFields);
	}

	handleFirstNameChange(e) {
		this.setState({firstName: e.target.value});
	}
	handleLastNameChange(e) {
		this.setState({lastName: e.target.value});
	}
	handleEmailChange(e) {
		this.setState({email: e.target.value});
	}
	handleRoleChange(e) {
		this.setState({roleName: e.target.value});
	}

	showErrorModal(message) {
		this.props.showErrorModal(message);
	}

	render() {

		var listOfRoles = this.props.roles.map(function(item) {
			return <option key={item} value={item}>{item}</option>
		});

		return (
			<div className="UserSearchPanel">
				<Panel>
					<Panel.Heading>
					<Panel.Toggle>
						<Panel.Title className="panelTitle">
							<Glyphicon glyph="glyphicon glyphicon-collapse-down" />
							&nbsp;
							Szukanie użytkowników
						</Panel.Title>
					</Panel.Toggle>
					 </Panel.Heading>
						<Panel.Body collapsible>
							<Form inline>
								<ControlLabel>Imię</ControlLabel>{' '}
								<FormControl value={this.state.firstName} onChange={this.handleFirstNameChange} type="text" placeholder="Jan" />{' '}
								<ControlLabel>Nazwisko</ControlLabel>{' '}
								<FormControl value={this.state.lastName} onChange={this.handleLastNameChange} type="text" placeholder="Kowalski" />{' '}
								<ControlLabel>Email</ControlLabel>{' '}
								<FormControl value={this.state.email} onChange={this.handleEmailChange} type="text" placeholder="email@example.com" />{' '}
								<FormControl componentClass="select" placeholder="Wybierz rolę" onChange={this.handleRoleChange}>
									<option value='' key='null'>---</option>
									{listOfRoles}
								</FormControl>{' '}
								<button type="button" className="btn btn-primary" onClick={this.handleSearch}>
									Szukaj
								</button>
							</Form>
						</Panel.Body>
				</Panel>
			</div>
		);
	}

}

export default UserSearchPanel;
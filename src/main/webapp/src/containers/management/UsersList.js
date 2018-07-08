import React, { Component } from 'react';
import './UsersList.css';

import { Table, Glyphicon, Button } from 'react-bootstrap';


class UsersList extends Component {
	constructor(props) {
		super(props);
		this.handleRemoveClick = this.handleRemoveClick.bind(this);
	}

	handleRemoveClick(id) {
		this.props.deleteUser(id);
	}

	render () {
		const isLoading= this.props.isLoading;

		if (isLoading) {
			return <p> Loading... </p>;
		}

		var listOfUsers = this.props.users.map((item) => {
			return <tr key={item.id}>
						<th>{item.id}</th>
						<th>{item.firstName}</th>
						<th>{item.lastName}</th>
						<th>{item.email}</th>
						<th>{item.roleName}</th>
						<th>
							<Button bsStyle="danger" onClick={(e) => this.handleRemoveClick(item.id, e)}  >
								<Glyphicon glyph='glyphicon glyphicon-remove' />
							</Button>
						</th>
					</tr>
		});
		return (
			<div className='UsersList'>
				<h2>Użytkownicy</h2>
				<Table responsive striped bordered condensed hover>
				<thead>
					<tr>
						<th>#</th>
						<th>Imię</th>
						<th>Nazwisko</th>
						<th>E-mail</th>
						<th>Rola</th>
						<th>Opcje</th>
					</tr>
				</thead>
				<tbody>
					{listOfUsers}
				</tbody>
				</Table>
			</div>
		);
	}
}

export default UsersList;
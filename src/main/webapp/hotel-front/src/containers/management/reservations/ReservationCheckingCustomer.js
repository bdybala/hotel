import React, {Component} from 'react';
import {
  Button,
  ControlLabel,
  Form,
  FormControl,
  Glyphicon
} from "react-bootstrap";

class ReservationCheckingCustomer extends Component {
  constructor(props) {
    super(props);

    this.handleFirstNameChange = this.handleFirstNameChange.bind(this);
    this.handleLastNameChange = this.handleLastNameChange.bind(this);
    this.handlePeselChange = this.handlePeselChange.bind(this);
    this.handleIdCardNumberChange = this.handleIdCardNumberChange.bind(this);
    this.handleRemoveClick = this.handleRemoveClick.bind(this);

    this.state = {};
  }

  handleFirstNameChange(e) {
    this.setState({firstName: e.target.value});
    this.props.onFirstNameChange(this.props.index, e.target.value);
  }

  handleLastNameChange(e) {
    this.setState({lastName: e.target.value});
    this.props.onLastNameChange(this.props.index, e.target.value);

  }

  handlePeselChange(e) {
    this.setState({pesel: e.target.value});
    this.props.onPeselChange(this.props.index, e.target.value);
  }

  handleIdCardNumberChange(e) {
    this.setState({idCardNumber: e.target.value});
    this.props.onIdCardNumberChange(this.props.index, e.target.value);
  }

  handleRemoveClick() {
    this.props.removeCheckedUser(this.props.index);
  }

  render() {
    return (<Form inline>
      <Button bsStyle="danger"
              onClick={(e) => this.handleRemoveClick()}>
        <Glyphicon glyph='glyphicon glyphicon-remove'/>
      </Button>{' '}
      <ControlLabel>Imię</ControlLabel>{' '}
      <FormControl value={this.props.user.firstName}
                   onChange={this.handleFirstNameChange} type="text"
                   placeholder="Jan"/>{' '}
      <ControlLabel>Nazwisko</ControlLabel>{' '}
      <FormControl value={this.props.user.lastName}
                   onChange={this.handleLastNameChange} type="text"
                   placeholder="Kowalski"/>{' '}
      <ControlLabel>Pesel</ControlLabel>{' '}
      <FormControl value={this.props.user.pesel}
                   onChange={this.handlePeselChange} type="number"
                   placeholder="90010112379"/>{' '}
      <ControlLabel>Nr Dowodu</ControlLabel>{' '}
      <FormControl value={this.props.user.idCardNumber}
                   onChange={this.handleIdCardNumberChange} type="text"
                   placeholder="CHN22333"/>{' '}
      <hr/>
    </Form>)
  }
}

export default ReservationCheckingCustomer;
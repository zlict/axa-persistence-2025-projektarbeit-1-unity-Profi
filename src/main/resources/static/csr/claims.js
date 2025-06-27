const URL = 'http://localhost:8080';
const FORM_MODES = { NEW: 'new', EDIT: 'edit' };
let formMode = FORM_MODES.NEW;
let claimForm;
let claims = [];
let vertraege = [];
let partners = [];
let claimHandlers = [];

const saveClaim = (e) => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const claim = {
    damageDate: formData.get('damageDate'),
    reportDate: formData.get('reportDate'),
    description: formData.get('description'),
    status: formData.get('status'),
    estimatedAmount: formData.get('estimatedAmount'),
    vertrag: { vertragId: formData.get('vertragId') },
    partner: formData.get('partnerId') ? { partnerId: formData.get('partnerId') } : null,
    claimHandlers: Array.from(document.querySelectorAll('input[name=claimHandlers]:checked')).map(cb => ({ claimHandlerId: cb.value }))
  };
  if (formMode === FORM_MODES.NEW) {
    fetch(`${URL}/claims`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(claim)
    }).then(result => result.json()).then(newClaim => {
      claims.push(newClaim);
      renderClaims();
    });
  } else if (formMode === FORM_MODES.EDIT) {
    fetch(`${URL}/claims/${formData.get('claimId')}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(claim)
    }).then(result => result.json()).then(updatedClaim => {
      claims = claims.map(c => c.claimId == updatedClaim.claimId ? updatedClaim : c);
      renderClaims();
    });
  }
  formMode = FORM_MODES.NEW;
  e.target.reset();
};

const deleteClaim = (id) => {
  fetch(`${URL}/claims/${id}`, { method: 'DELETE' }).then(() => {
    claims = claims.filter(c => c.claimId != id);
    renderClaims();
  });
};

const editClaim = (id) => {
  formMode = FORM_MODES.EDIT;
  const claim = claims.find(c => c.claimId == id);
  claimForm.querySelector('input[name=claimId]').value = claim.claimId;
  claimForm.querySelector('input[name=damageDate]').value = claim.damageDate;
  claimForm.querySelector('input[name=reportDate]').value = claim.reportDate;
  claimForm.querySelector('input[name=description]').value = claim.description;
  claimForm.querySelector('input[name=status]').value = claim.status;
  claimForm.querySelector('input[name=estimatedAmount]').value = claim.estimatedAmount;
  claimForm.querySelector('select[name=vertragId]').value = claim.vertrag?.vertragId;
  claimForm.querySelector('select[name=partnerId]').value = claim.partner?.partnerId || '';
  // Set claimHandlers checkboxes
  const handlerIds = (claim.claimHandlers || []).map(h => h.claimHandlerId + '');
  document.querySelectorAll('input[name=claimHandlers]').forEach(cb => {
    cb.checked = handlerIds.includes(cb.value);
  });
};

const indexClaims = () => {
  fetch(`${URL}/claims`).then(r => r.json()).then(result => {
    claims = result;
    renderClaims();
  });
  renderClaims();
};

const indexVertraege = () => {
  fetch(`${URL}/vertraege`).then(r => r.json()).then(result => {
    vertraege = result;
    renderVertraege();
  });
};

const indexPartners = () => {
  fetch(`${URL}/partners`).then(r => r.json()).then(result => {
    partners = result;
    renderPartners();
  });
};

const indexClaimHandlers = () => {
  fetch(`${URL}/claim-handlers`).then(r => r.json()).then(result => {
    claimHandlers = result;
    renderClaimHandlers();
  });
};

const renderVertraege = () => {
  const select = document.querySelector('#vertragSelect');
  select.innerHTML = '';
  vertraege.forEach(v => {
    const option = document.createElement('option');
    option.value = v.vertragId;
    option.innerText = `${v.policyNumber} (${v.productName})`;
    select.appendChild(option);
  });
};

const renderPartners = () => {
  const select = document.querySelector('#partnerSelect');
  select.innerHTML = '<option value="">None</option>';
  partners.forEach(p => {
    const option = document.createElement('option');
    option.value = p.partnerId;
    option.innerText = p.name;
    select.appendChild(option);
  });
};

const renderClaimHandlers = () => {
  const div = document.querySelector('#claimHandlersSelect');
  div.innerHTML = '';
  if (claimHandlers.length === 0) {
    div.innerText = 'No claim handlers';
    return;
  }
  claimHandlers.forEach(h => {
    const label = document.createElement('label');
    label.style.marginRight = '10px';
    const cb = document.createElement('input');
    cb.type = 'checkbox';
    cb.name = 'claimHandlers';
    cb.value = h.claimHandlerId;
    label.appendChild(cb);
    label.appendChild(document.createTextNode(`${h.firstName} ${h.lastName}`));
    div.appendChild(label);
  });
};

const createCell = (text) => {
  const cell = document.createElement('td');
  cell.innerText = text;
  return cell;
};

const createActionsCell = (id) => {
  const cell = document.createElement('td');
  const deleteButton = document.createElement('button');
  deleteButton.innerText = "Delete";
  deleteButton.addEventListener('click', () => deleteClaim(id));
  cell.appendChild(deleteButton);
  const editButton = document.createElement('button');
  editButton.innerText = "Edit";
  editButton.addEventListener('click', () => editClaim(id));
  cell.appendChild(editButton);
  return cell;
};

const renderClaims = () => {
  const display = document.querySelector('#claimDisplay');
  display.innerHTML = '';
  claims.forEach(claim => {
    const row = document.createElement('tr');
    row.appendChild(createCell(claim.claimId));
    row.appendChild(createCell(claim.damageDate));
    row.appendChild(createCell(claim.reportDate));
    row.appendChild(createCell(claim.description));
    row.appendChild(createCell(claim.status));
    row.appendChild(createCell(claim.estimatedAmount));
    row.appendChild(createCell(claim.vertrag ? claim.vertrag.policyNumber : ''));
    row.appendChild(createCell(claim.partner ? claim.partner.name : ''));
    row.appendChild(createCell((claim.claimHandlers || []).map(h => h.firstName + ' ' + h.lastName).join(', ')));
    row.appendChild(createActionsCell(claim.claimId));
    display.appendChild(row);
  });
};

document.addEventListener('DOMContentLoaded', function () {
  claimForm = document.querySelector('#claimForm');
  claimForm.addEventListener('submit', saveClaim);
  indexClaims();
  indexVertraege();
  indexPartners();
  indexClaimHandlers();

  const filterBtn = document.getElementById('filterClaimsBtn');
  const clearBtn = document.getElementById('clearFilterBtn');
  const filterCustomerId = document.getElementById('filterCustomerId');
  const filterFirstName = document.getElementById('filterFirstName');

  filterBtn.addEventListener('click', function() {
    const customerId = filterCustomerId.value.trim();
    const firstName = filterFirstName.value.trim();
    let url = `${URL}/claims`;
    const params = [];
    if (customerId) params.push(`customerId=${encodeURIComponent(customerId)}`);
    if (firstName) params.push(`firstName=${encodeURIComponent(firstName)}`);
    if (params.length > 0) url += '?' + params.join('&');
    fetch(url).then(r => r.json()).then(result => {
      claims = result;
      renderClaims();
    });
  });

  clearBtn.addEventListener('click', function() {
    filterCustomerId.value = '';
    filterFirstName.value = '';
    indexClaims();
  });
});

document.addEventListener('DOMContentLoaded', function() {
  const grid = document.getElementById('roomGrid');

  grid.addEventListener('click', function(e){
    const tile = e.target.closest('.tile');
    if(!tile) return;
    const id = tile.dataset.id;
    const status = tile.dataset.status;
    const type = tile.dataset.type;
    alert('Mã phòng: ' + tile.querySelector('.code').innerText + '\nID: ' + id + '\nLoại: ' + type + '\nTrạng thái: ' + status);
    // thực tế: hiển thị modal chi tiết hoặc gọi API
  });

  // status filter
  document.querySelectorAll('.status-buttons button').forEach(btn=>{
    btn.addEventListener('click', function(){
      const s = this.dataset.status;
      document.querySelectorAll('.room-grid .tile').forEach(t=>{
        if(!s) t.style.display = '';
        else t.style.display = (t.dataset.status === s) ? '' : 'none';
      });
    });
  });

  // type filter
  const typeSelect = document.getElementById('roomTypeFilter');
  typeSelect.addEventListener('change', function(){
    const t = this.value;
    document.querySelectorAll('.room-grid .tile').forEach(tile=>{
      if(!t) tile.style.display = '';
      else tile.style.display = (tile.dataset.type === t) ? '' : 'none';
    });
  });
});
